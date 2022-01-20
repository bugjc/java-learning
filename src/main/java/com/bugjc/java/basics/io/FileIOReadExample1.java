package com.bugjc.java.basics.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * 文件读取
 * @author aoki
 * @date 2022/1/14
 * **/
public class FileIOReadExample1 {

    public static void main(String[] args) throws IOException {
        String fileName = "D:\\tmp\\111.txt";

        //使用 FileInputStream 读取大型二进制文件
        try (FileInputStream is = new FileInputStream(fileName)) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.println("Byte: " + b);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------");

        //使用 NIO.2 InputStream 读取大型二进制文件
        try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.println("Byte: " + b);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------");

        //使用 BufferedInputStream 更快地读取
        try (FileInputStream is = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(is, 16384)) {
            int b;
            while ((b = bis.read()) != -1) {
                System.out.println("Byte: " + b);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------");

        //使用 FileReader 读取大型文本文件
        try (FileInputStream is = new FileInputStream(fileName);
             InputStreamReader reader = new InputStreamReader(is)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println("Char: " + (char) c);
            }
        }

        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println("Char: " + (char) c);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------");

        //使用 BufferedReader 更快地读取文本文件
        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader((reader))) {
            int c;
            while ((c = bufferedReader.read()) != -1) {
                System.out.println("Char: " + (char) c);
            }
        }

        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader((reader))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Line: " + line);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------");

        //使用 NIO.2 BufferedReader 更快地读取文本文件
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println("Char: " + (char) c);
            }
        }
    }
}
