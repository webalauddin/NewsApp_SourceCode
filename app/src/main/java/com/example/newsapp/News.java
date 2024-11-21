package com.example.newsapp;

import static com.example.newsapp.NoInternet.toast;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

import soup.neumorphism.NeumorphFloatingActionButton;

public class News extends AppCompatActivity {


    TextView NewsTitle, NewsDes;
    ImageView NewsImage;
    NeumorphFloatingActionButton NewsMice;


    public static String TITLE = "";
    public static String DESCRIPTION = "";
    public static Bitmap My_Bitmap = null;


    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        NewsTitle = findViewById(R.id.NewsTitle);
        NewsDes = findViewById(R.id.NewsDes);
        NewsImage = findViewById(R.id.NewsImage);
        NewsMice = findViewById(R.id.NewsMice);



        NewsTitle.setText(TITLE);
        NewsDes.setText(DESCRIPTION);

        if (My_Bitmap!=null) NewsImage.setImageBitmap(My_Bitmap);



        textToSpeech = new TextToSpeech(News.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {



            }
        });


        NewsMice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textToSpeech.speak(NewsDes.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                toast(News.this, "Voice On");

            }
        });


        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        NewsMice.setBackgroundColor(color);




    } //===========================================//






    @Override
    public void onBackPressed() {

        if(textToSpeech!=null)textToSpeech.stop();
        super.onBackPressed();

    }
}