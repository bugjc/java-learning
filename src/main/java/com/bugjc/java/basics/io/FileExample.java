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

        Path tempDir = Path.of(System.getProperty("java.io.tmpdir"));
        System.out.println(tempDir);

        Path homeDir = Path.of(System.getProperty("user.home"));
        System.out.println(homeDir);

        Path tempFile = Files.createTempFile("happycoders-", ".tmp");
        System.out.println(tempFile);

        Path targetDir = Path.of(System.getProperty("user.home"));
        Path target = targetDir.resolve(tempFile.getFileName());
        Files.move(tempFile, target);

        Path tempDir1 = Files.createTempDirectory("happycoders-");
        Path tempDir2 = Files.createTempDirectory("happycoders-");
        target = tempDir2.resolve(tempDir1.getFileName());
        Files.move(tempDir1, target);

        try {
            tempFile = Files.createTempFile("happycoders-", ".tmp");
            target = tempFile.resolveSibling("happycoders.tmp");
            Files.move(tempFile, target);
        } catch (FileAlreadyExistsException ignore){

        }

        tempFile = Files.createTempFile("happycoders-", ".tmp");
        targetDir = Path.of(System.getProperty("user.home"));
        target = targetDir.resolve(tempFile.getFileName());
        Files.copy(tempFile, target);

        tempFile = Files.createTempFile("happycoders-", ".tmp");
        Files.delete(tempFile);

        try {
            tempDir = Files.createTempDirectory("happycoders-");
            tempFile = Files.createTempFile(tempDir, "happycoders-", ".tmp");
            Files.delete(tempDir);
        } catch (DirectoryNotEmptyException ignore){
        }

        tempFile = Files.createTempFile("happycoders-", ".tmp");
        Path linkDir = Paths.get(System.getProperty("user.home"));
        Path link = linkDir.resolve(tempFile.getFileName());
        Files.createSymbolicLink(link, tempFile);
        System.out.println(tempFile);
        System.out.println(link);



        //Files.list(homeDir).forEach(System.out::println);
        //findFileRecursively(homeDir, "settings");
        //findFileWithWalk(homeDir, "settings");
        //findFileWithWalkFileTree(homeDir, "settings");
        //findFileWithFind(homeDir, "settings");

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
