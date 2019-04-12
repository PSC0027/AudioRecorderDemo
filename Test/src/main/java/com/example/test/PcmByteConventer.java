package com.example.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.DataInputStream;

public class PcmByteConventer {
    public static byte[] load16BitPCMRawDataFileAsByteArray(File file) {
        if (file.isFile()) {
            long size = file.length();
            try {
                FileInputStream in = new FileInputStream(file);
                return readStreamAsByteArray(in,size);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static byte[] readStreamAsByteArray(InputStream in, long size)
            throws IOException {
        int bufferSize = (int) (size / 2);
        byte[] result = new byte[bufferSize];
        DataInputStream is = new DataInputStream(in);
        for (int i = 0; i < bufferSize; i++) {
            result[i] = is.readByte();
        }
        return result;
    }
}
