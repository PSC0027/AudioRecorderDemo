package com.example.test;

public class ByteArrayToDoubleArray {
    public static double[] LoadByteArrayToDoubleArray(byte[] bytes){

        double[] result = new double[bytes.length/2];

        for(int i = 0;i < (bytes.length/2);i++){
            byte bl = bytes[ 2 * i ];
            byte bh = bytes[ 2 * i + 1];
            short s = (short) ((bh & 0x00FF) << 8 | bl & 0x00FF);

            System.out.println("s [" + i + "] = " + s);

            result[i] = s / 32768.0;

            System.out.println("result [" + i + "] = " +  result[i]);
        }

        return result;
    }
}
