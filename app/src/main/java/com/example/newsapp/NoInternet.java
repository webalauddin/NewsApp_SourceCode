package com.example.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NoInternet extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_no_internet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info = manager.getActiveNetworkInfo();

                    if (info!=null && info.isConnected()){

                        startActivity(new Intent(NoInternet.this, MainActivity.class));
                        finish();
                        toast(NoInternet.this, "Internet connected");

                    } else {

                        toast(NoInternet.this, "Please check your internet and try again!");
                    }





                }
            });






    }


    public static void toast (Activity activity, String string){

        Toast.makeText(activity, ""+string, Toast.LENGTH_SHORT).show();

    }
}