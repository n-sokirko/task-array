package com.taskarray;

public class FirstMain {
    public static void main (String[] args)  {
        int popa = 1_000_000;
        final byte b = 1;
        final byte b2 = 2;
        byte b3 = (byte)(b+b2);
        long k = 100_000_000_00000000L;
        int b16 = 0xffff;
        char dok = 'a'+54;
        String str = new String("java");
        str = str.intern();
        String str1 = "java";


       System.out.println(str == str1);
    }
}
