package com.example.audiorecorderdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 123;

    Button startButton, stopButton;

    private final static int AUDIO_INPUT  = MediaRecorder.AudioSource.MIC;
    private static int sampleRateInHz = 44100;
    private int bufferSizeInByte = AudioRecord.getMinBufferSize(sampleRateInHz, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord mAudioRecord;
    private boolean isRecord = false;
    private AudioRecordThread mAudioRecordThread;

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if(requestCode == REQUEST_CODE){
//
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Log.d("Clima","onRequestPermissionsResult(): Permission granted");
//                new AudioRecordThread(mAudioRecord,bufferSizeInBytes,isRecord).start();
//            }else{
//                Log.d("Clima","Permission denied");
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);

        Log.d("AudioRecordDemo","bufferSizeInFloat is " + bufferSizeInByte);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRateInHz,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,bufferSizeInByte);
                mAudioRecord.startRecording();
                Log.d("AudioRecorderDemo","Record id Started.");

                isRecord = true;

//                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
//
//                    ActivityCompat.requestPermissions(getParent(),
//                            new String[] {Manifest.permission.RECORD_AUDIO},REQUEST_CODE);
//                    return;
//                }
//
//                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(getParent(),
//                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
//                    return;
//                }

                mAudioRecordThread = new AudioRecordThread(mAudioRecord,bufferSizeInByte,isRecord);
                mAudioRecordThread.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mAudioRecord != null){
                    mAudioRecordThread.setRecord(false);
                    mAudioRecord.stop();
                    mAudioRecord.release();
                    mAudioRecord = null;

                    Log.d("AudioRecordDemo","Stop Record");
                }
            }
        });




    }

}
