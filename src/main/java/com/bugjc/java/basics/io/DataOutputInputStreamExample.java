package com.bugjc.java.basics.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataOutputInputStreamExample {
    public static void main(String[] args) throws IOException {

        System.out.println("------------------------------test1.bin-------------------------------------");

        //使用 DataOutputStream 编写结构化数据
        try (var out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("test1.bin")))) {
            out.writeByte((byte) 123);
            out.writeShort((short) 1_234);
            out.writeInt(1_234_567);
            out.writeLong(1_234_567_890_123_456L);
            out.writeFloat((float) Math.E);
            out.writeDouble(Math.PI);
            out.writeBoolean(true);
            out.writeChar('€');
        }

        //使用 DataInputStream 读取结构化数据
        try (var in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("test1.bin")))) {
            System.out.println(in.readByte());
            System.out.println(in.readShort());
            System.out.println(in.readInt());
            System.out.println(in.readLong());
            System.out.println(in.readFloat());
            System.out.println(in.readDouble());
            System.out.println(in.readBoolean());
            System.out.println(in.readChar());
        }

        System.out.println("------------------------------test2.bin-------------------------------------");

        //writeByte() 和 writeShort() 的不同数据类型
        try (var out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("test2.bin")))) {
            out.writeByte(1000);  // --> e8
            out.writeByte(128);   // --> 80
            out.writeByte(127);   // --> 7f (Byte.MAX_VALUE)
            out.writeByte(0);     // --> 00
            out.writeByte(-128);  // --> 80 (Byte.MIN_VALUE)
            out.writeByte(-129);  // --> 7f
            out.writeByte(-1000); // --> 18
        }
        try ( var out = new DataOutputStream( new BufferedOutputStream(
                new FileOutputStream( "test2.bin" )))) {
            out.writeInt(1000);  // --> 00 00 03 e8
            out.writeInt(128);   // --> 00 00 00 80
            out.writeInt(127);   // --> 00 00 00 7f
            out.writeInt(0);     // --> 00 00 00 00
            out.writeInt(-128);  // --> ff ff ff 80
            out.writeInt(-129);  // --> ff ff ff 7f
            out.writeInt(-1000); // --> ff ff fc 18
        }
        try ( var out = new DataOutputStream( new BufferedOutputStream(
                new FileOutputStream( "test2.bin" )))) {
            out.writeShort(1000000);  // --> 42 40 (int: 00 0f 42 40 )
            out.writeShort(32768);    // --> 80 00 (int: 00 00 80 00 )
            out.writeShort(32767);    // --> 7f ff (int: 00 00 7f ff )
            out.writeShort(0);        // --> 00 00 (int: 00 00 00 00 )
            out.writeShort(-32768);   // --> 80 00 (int: ff ff 80 00 )
            out.writeShort(-32769);   // --> 7f ff (int: ff ff 7f ff )
            out.writeShort(-1000000); // --> bd c0 (int: ff f0 bd c0 )
        }

        System.out.println("------------------------------test5.bin-------------------------------------");

        //writeChar() 的不同数据类型
        try ( var out = new DataOutputStream( new BufferedOutputStream(
                new FileOutputStream( "test5.bin" )))) {
            out.writeChar( 'a' );  // --> 00 61
            out.writeChar( '€' );  // --> 20 ac
            out.writeChar( '字' ); // --> 5b 57

            out.writeChar(723_790_628); // --> 2b 24 (int: 2b 24 2b 24 )
            out.writeChar(-100);        // --> ff 9c (int: ff ff ff 9c )
            out.writeChar(-16_776_261); // --> 03 bb (int: ff 00 03 bb )
        }
        try (var in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("test5.bin")))) {
            System.out.println(in.readChar());
            System.out.println(in.readChar());
            System.out.println(in.readChar());
            System.out.println(in.readChar());
            System.out.println(in.readChar());
            System.out.println(in.readChar());
        }

        System.out.println("-------------------------------test6.bin------------------------------------");

        //使用 DataOutputStream 编写字符串
        final String str = "Hello World äöü ß α € ↖ 🔥";
        try (var out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("test6.bin")))) {
            out.writeBytes(str);
            out.writeChars(str);
            out.writeUTF(str);
        }

        System.out.println("------------------------------test8.bin-------------------------------------");

        try (var out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("test8.bin")))) {
            out.writeInt(str.length());
            out.writeBytes(str);

            out.writeInt(str.length());
            out.writeChars(str);

            out.writeUTF(str);
        }

        try (var in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("test8.bin")))) {
            // read String written by writeBytes()
            int len = in.readInt();
            byte[] bytes = new byte[len];
            in.read(bytes, 0, len);
            String s = new String(bytes, StandardCharsets.ISO_8859_1);
            System.out.println(s);

            // read String written by writeChars()
            len = in.readInt();
            bytes = new byte[len * 2];
            in.read(bytes, 0, len * 2);
            s = new String(bytes, StandardCharsets.UTF_16BE);
            System.out.println(s);

            // read String written by writeUTF()
            s = in.readUTF();
            System.out.println(s);
        }
    }
}
