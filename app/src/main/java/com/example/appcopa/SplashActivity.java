package com.example.appcopa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        VideoView video=findViewById(R.id.videoView);

        Uri videoUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_screen);

        video.setVideoURI(videoUri);

        video.setOnPreparedListener(mp ->{
            mp.setLooping(false);
            video.start();
        });

        video.setOnCompletionListener(mp->{
            Intent intent = new Intent(SplashActivity.this,SelectLanguage.class);
            startActivity(intent);
            finish();
        });
    }
}