package com.bugjc.java.basics.io;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileExample {

    public static void main(String[] args) throws IOException {
        String homePath = System.getProperty("user.home");
        String fileName = "test" + System.currentTimeMillis();
        String filePath = homePath + File.separator + fileName;
        System.out.println("filePath = " + filePath);

        File file = new File("test" + System.currentTimeMillis());
        System.out.println(file.getAbsolutePath());

        file = new File("dummy");
        File absoluteFile = file.getAbsoluteFile();
        File absoluteDirectory = absoluteFile.getParentFile();
        System.out.println(absoluteDirectory);

        File currentDir = new File(System.getProperty("user.dir"));
        System.out.println(currentDir);

        Path currentDir1 = Path.of(System.getProperty("user.home"));
        Files.list(currentDir1).forEach(System.out::println);

        //findFileRecursively(currentDir1, "settings");
        //findFileWithWalk(currentDir1, "settings");
        //findFileWithWalkFileTree(currentDir1, "settings");
        findFileWithFind(currentDir1, "settings");
    }

    private static void findFileRecursively(
            Path currentDir, String fileNamePrefix) throws IOException {
        Files.list(currentDir).forEach(child -> {
            if (Files.isRegularFile(child)
                    && child.getFileName().toString().startsWith(fileNamePrefix)) {
                System.out.println(child);
            }

            if (Files.isDirectory(child) ) {
                try {
                    findFileRecursively(child, fileNamePrefix);
                } catch (AccessDeniedException e) {
                    System.out.println("Access denied: " + child);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        });
    }

    private static void findFileWithWalk(Path currentDir, String fileNamePrefix)
            throws IOException {
        Files.walk(currentDir).forEach(child -> {
            if (Files.isRegularFile(child)
                    && child.getFileName().toString().startsWith(fileNamePrefix)) {
                System.out.println(child);
            }
        });
    }

    private static void findFileWithWalkFileTree(Path currentDir, String fileNamePrefix) throws IOException {
        Files.walkFileTree(currentDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                if (Files.isRegularFile(file)
                        && file.getFileName().toString().startsWith(fileNamePrefix)) {
                    System.out.println(file);
                }
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                if (exc instanceof AccessDeniedException) {
                    System.out.println("Access denied: " + file);
                    return FileVisitResult.CONTINUE;
                } else if (exc instanceof FileSystemException){
                    return FileVisitResult.SKIP_SUBTREE;
                } else {
                    return super.visitFileFailed(file, exc);
                }
            }
        });
    }

    private static void findFileWithFind(Path currentDir, String fileNamePrefix)
            throws IOException {
        Files.find(currentDir, Integer.MAX_VALUE,
                (path, attributes) -> Files.isRegularFile(path)
                        && path.getFileName().toString().startsWith(fileNamePrefix))
                .forEach(System.out::println);
    }
}
