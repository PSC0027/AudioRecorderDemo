package com.example.audiorecorderdemo;

import android.media.AudioRecord;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class AudioRecordThread extends Thread {

    AudioRecord mAudioRecord;
    int bufferSizeInBytes;
    private boolean isRecord;

    public void setRecord(boolean record) {
        isRecord = record;
    }

    public AudioRecordThread(AudioRecord audioRecord, int bufferSizeInBytes, boolean isRecord) {
        mAudioRecord = audioRecord;
        this.bufferSizeInBytes = bufferSizeInBytes;
        this.isRecord = isRecord;
    }

    @Override
    public void run() {
        super.run();

        writeDataToFile();
//        copyWaveFile("sdcard/Demo.pcm","sdcard/Demo.wav");
    }

    private void writeDataToFile(){
        byte[] audioData = new byte[bufferSizeInBytes];
        int readSize = 0;
        FileOutputStream fos = null;

        File file = new File("/sdcard/Demo.pcm");
        Log.d("AudioRecorderDemo","File path is " + file.getAbsolutePath());

        if(file.exists()) file.delete();
        Log.d("AudioRecorderDemo","File path is " + file.getAbsolutePath());

        try{
            fos = new FileOutputStream(file);
            Log.d("AudioRecorderDemo","FileOutputStream work.");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while(isRecord == true)
        {
            readSize = mAudioRecord.read(audioData, 0, bufferSizeInBytes);
            Log.d("AudioRecorderDemo","Read Data from mAudioRecord.");

            if(AudioRecord.ERROR_INVALID_OPERATION != readSize){
                try{
                    fos.write(audioData);
                    Log.d("AudioRecorderDemo","Write data to file");
                    Log.d("AudioRecorderDemo","File size: " + file.length());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        try {
            fos.close();
            Log.d("AudioRecorderDemo","FileOutputStream end.");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    private void copyWaveFile(String inFileName, String outFileName){
//        FileInputStream in = null;
//        FileOutputStream out = null;
//        long totalAudioLen = 0;
//        long totalDataLen = totalAudioLen + 36;
//        long longSampleRate = 44100;
//        int channels = 2;
//        long byteRate = 16 * 44100 * channels / 8;
//        byte[] data = new byte[bufferSizeInBytes];
//        try{
//            in = new FileInputStream(inFileName);
//            out = new FileOutputStream(outFileName);
//            totalAudioLen = in.getChannel().size();
//            totalDataLen = totalAudioLen + 36;
//            WriteWaveFileHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
//            while(in.read(data) != -1){
//                out.write(data);
//            }
//            in.close();
//            out.close();
//        } catch (FileNotFoundException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e)		{
//
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen, long totalDataLen, long longSampleRate, int channels, long byteRate)
//            throws IOException {
//        byte[] header = new byte[44];
//        header[0] = 'R'; // RIFF/WAVE header
//        header[1] = 'I';
//        header[2] = 'F';
//        header[3] = 'F';
//        header[4] = (byte) (totalDataLen & 0xff);
//        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
//        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
//        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
//        header[8] = 'W';
//        header[9] = 'A';
//        header[10] = 'V';
//        header[11] = 'E';
//        header[12] = 'f'; // 'fmt ' chunk
//        header[13] = 'm';
//        header[14] = 't';
//        header[15] = ' ';
//        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
//        header[17] = 0;
//        header[18] = 0;
//        header[19] = 0;
//        header[20] = 1; // format = 1
//        header[21] = 0;
//        header[22] = (byte) channels;
//        header[23] = 0;
//        header[24] = (byte) (longSampleRate & 0xff);
//        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
//        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
//        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
//        header[28] = (byte) (byteRate & 0xff);
//        header[29] = (byte) ((byteRate >> 8) & 0xff);
//        header[30] = (byte) ((byteRate >> 16) & 0xff);
//        header[31] = (byte) ((byteRate >> 24) & 0xff);
//        header[32] = (byte) (2 * 16 / 8); // block align
//        header[33] = 0;
//        header[34] = 16; // bits per sample
//        header[35] = 0;
//        header[36] = 'd';
//        header[37] = 'a';
//        header[38] = 't';
//        header[39] = 'a';
//        header[40] = (byte) (totalAudioLen & 0xff);
//        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
//        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
//        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
//        out.write(header, 0, 44);
//    }
}
