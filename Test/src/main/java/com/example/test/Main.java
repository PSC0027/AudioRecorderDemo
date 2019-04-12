package com.example.test;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File mFile = new File("C:\\Users\\ThinkPad\\Desktop\\Demo.pcm");
        System.out.println("Load finish");

        byte[] bmf = PcmByteConventer.load16BitPCMRawDataFileAsByteArray(mFile);

        double[] db = ByteArrayToDoubleArray.LoadByteArrayToDoubleArray(bmf);


    }
}
