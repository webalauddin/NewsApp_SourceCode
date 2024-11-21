package com.example.newsapp;

import static com.example.newsapp.NoInternet.toast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout adContainer;
    AdView adView;
    GridView gridView;
    ArrayList <HashMap <String, String> > arrayList = new ArrayList();
    HashMap <String, String> hashMap = new HashMap();
    int BannerAdClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adContainer = findViewById(R.id.adContainer);
        gridView = findViewById(R.id.gridview);


        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();


        if (info!=null && info.isConnected()){

            hashList();
            MyAdapter myAdapter = new MyAdapter();
            gridView.setAdapter(myAdapter);

        } else {

            startActivity(new Intent(MainActivity.this, NoInternet.class));
        }







        BannerAds();





    }//setContentView Close===============//






    public void BannerAds(){



        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);

        adContainer.addView(adView);
        //adView.loadAd();



        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

                toast(MainActivity.this, "Error "+adError.getErrorMessage());

            }

            @Override
            public void onAdLoaded(Ad ad) {
                toast(MainActivity.this, "AdLoaded");
            }

            @Override
            public void onAdClicked(Ad ad) {

                BannerAdClick++;
                toast(MainActivity.this, "AdClicked"+BannerAdClick);

                if (BannerAdClick >= 2){

                    adView.destroy();
                    adContainer.setVisibility(View.GONE);

                }


            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };



        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());








    }// Banner Ads Close ====================//





    //Adapter Class Start-----------------------------//
    //Adapter Class Start-----------------------------//
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item, parent, false);



            //Introduction Part-------------//
            //Introduction Part-------------//

            ImageView img = myView.findViewById(R.id.img);
            TextView cat = myView.findViewById(R.id.cat);
            TextView title = myView.findViewById(R.id.title);
            TextView des = myView.findViewById(R.id.des);
            LinearLayout MainLayout = myView.findViewById(R.id.MainLayout);

            //Introduction Part-------------//
            //Introduction Part-------------//

            HashMap<String, String> hashMap = arrayList.get(position);

            String Image = hashMap.get("IMAGE");
            String Category = hashMap.get("CAT");
            String Title = hashMap.get("TITLE");
            String Description = hashMap.get("DESCRIPTION");


            Picasso.get().load(Image)
                    .placeholder(R.drawable.logo)
                    .into(img);

            MainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    News.TITLE = Title;
                    News.DESCRIPTION = Description;
                    Bitmap bitmap = ( (BitmapDrawable) img.getDrawable() ).getBitmap();

                    News.My_Bitmap = bitmap;

                    startActivity(new Intent(MainActivity.this, News.class));


                }
            }); //MainLayout OnClick Close---------------------//







            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            cat.setBackgroundColor(color);

            return myView;
        }
    }
    //Adapter Class Close-----------------------------//
    //Adapter Class Close-----------------------------//



    //ArrayList Start==============================//
    //ArrayList Start==============================//


    private void hashList(){

        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://www.kalerkantho.com/_next/image?url=https%3A%2F%2Fcdn.kalerkantho.com%2Fpublic%2Fnews_images%2F2024%2F04%2F23%2F1713864795-9722c8cccf2573e13d4b0465417833f0.jpg&w=828&q=100");
        hashMap.put("CAT", "gold rate");
        hashMap.put("TITLE", "সোনার দাম ভরিতে কমল ৩১৩৮ টাকা");
        hashMap.put("DESCRIPTION", "দেশের বাজারে কমল সোনার দাম। দাম কমে এখন থেকে দেশের বাজারে ভালো মানের সোনা প্রতি ভরি (২২ ক্যারেট) বিক্রি হবে এক লাখ ১৬ হাজার ২৯০ টাকায়। নতুন করে প্রতি ভরি ভালো মানের সোনার দাম কমেছে তিন হাজার ১৩৮ টাকা। মঙ্গলবার (২৩ এপিল) বিকাল ৪টা থেকেই সারা দেশে সোনার নতুন এ দর কার্যকর হবে। বাংলাদেশ জুয়েলার্স অ্যাসোসিয়েশন (বাজুস) এক সংবাদ বিজ্ঞপ্তিতে সোনার এ দাম কমার তথ্য জানায়। এতে বলা হয়, স্থানীয় বাজারে তেজাবী (পিওর গোল্ড) সোনার মূল্য হ্রাস পেয়েছে। সে কারণে সোনার দাম সমন্বয়ের সদ্ধিান্ত নিয়েছে বাজুস।\n" +
                "\n" +
                "নতুন মূল্য অনুযায়ী ২১ ক্যারেটের এক ভরি সোনার দাম কমে এক লাখ ১০ হাজার ৯৯৫ টাকা নির্ধারণ করা হয়েছে। এ ছাড়া ১৮ ক্যারেটের এক ভরি সোনার দাম ৯৫ হাজার ১৪৩ টাকা নির্ধারণ করা হয়েছে। আর সনাতন পদ্ধতির এক ভরি সোনার দাম ৭৬ হাজার ৫৮৬ টাকা করা হয়েছে।\n" +
                "অপরিবর্তীত রয়েছে রুপার দাম। ক্যাটাগরি অনুযায়ী বর্তমানে ২২ ক্যারেটের রুপার দাম ভরি দুই হাজার ১০০ টাকা। ২১ ক্যারেটের রুপার দাম ভরি দুই হাজার ৬ টাকা এবং ১৮ ক্যারেটের রুপার দাম ভরি এক হাজার ৭১৫ টাকা নির্ধারণ করা হয়। আর সনাতন পদ্ধতির এক ভরি রুপার দাম এক হাজার ২৮৩ টাকা নির্ধারণ করা হয়েছে।");
        arrayList.add(hashMap);




        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://www.kalerkantho.com/_next/image?url=https%3A%2F%2Fcdn.kalerkantho.com%2Fpublic%2Fnews_images%2F2024%2F04%2F23%2F1713864473-2bd0b73063df5b2f94ff7edbd8fc5600.jpg&w=828&q=100");
        hashMap.put("CAT", "elephant dead");
        hashMap.put("TITLE", "মহাসড়কের পাশে পড়েছিল হাতির মরদেহ, উদ্ধারে বন বিভাগ");
        hashMap.put("DESCRIPTION", "গাজীপুরের রাজেন্দ্রপুর ভাওয়াল জাতীয় উদ্যান এলাকায় মহাসড়কের পাশ থেকে একটি হাতির মরদেহ উদ্ধার করেছে বন বিভাগ। আজ মঙ্গলবার (২৩ এপ্রিল) সকালে রাজেন্দ্রপুর ভাওয়াল জাতীয় উদ্যানের ৩ নম্বর গেটের বিপরীতে ঢাকা-ময়মনসিংহ মহাসড়কের পাশে হাতির মরদেহ পড়ে থাকার খবর পেয়ে ঘটনাস্থলে যান বন বিভাগের কর্মকর্তারা।\n" +
                "\n" +
                "বন্য প্রাণী ও জীববৈচিত্র্য সংরক্ষণ কর্মকর্তা রুবিয়া ইসলাম বলেন, আজ মঙ্গলবার (২৩ এপ্রিল) সকালে ভাওয়াল জাতীয় উদ্যানের কর্মকর্তা-কর্মচারীরা মহাসড়কের পাশে হাতির মরদেহ পড়ে থাকতে দেখে আমাদের বিষয়টি জানান। তাৎক্ষণিকভাবে বিষয়টি ঊর্ধ্বতন কর্মকর্তাদের জানানো হয়। এরপর পুলিশে খবর দেওয়া হয়। রুবিয়া ইসলাম আরো বলেন, আমরা ঘটনাস্থলে এসে দেখতে পাই, মৃত হাতির মাথার মধ্যে ক্ষতচিহ্ন আছে, দাঁতগুলো উঠিয়ে নিয়ে গেছে। ইতিমধ্যে পুলিশের একটি ফরেনসিক টিম ঘটনাস্থলের উদ্দেশে রওনা দিয়েছে। আর গাজীপুরের সদর থানা পুলিশ ও ট্যুরিস্ট পুলিশের একটি টিম ঘটনাস্থলে আছে। তিনি বলেন, পুলিশের ফরেনসিক টিম তথ্য সংগ্রহের পর ভেটেরিনারি সার্জন ও কৃষি বিশ্ববিদ্যালয়ের একজন ভেটেরিনারি সার্জন যৌথভাবে হাতির পোস্টমর্টেম করবেন। এরপর হাতির মৃত্যুর আসল কারণ জানা যাবে। হাতিটি বন্য নয়, বরং পোষা হাতি। প্রাথমিকভাবে ধারণা করছি, অন্য কোথাও হাতির মৃত্যুর পর কেউ এখানে এনে ফেলে রেখে গিয়েছে।তদন্তের পর এ বিষয়ে আইনগত ব্যবস্থা নেওয়া হবে।");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://www.kalerkantho.com/_next/image?url=https%3A%2F%2Fcdn.kalerkantho.com%2Fpublic%2Fnews_images%2F2024%2F04%2F23%2F1713855287-ebbffc585cb84300153083a5f948da02.jpg&w=828&q=100");
        hashMap.put("CAT", "mustafiz");
        hashMap.put("TITLE", "মুস্তাফিজ ফিরে এলে দুঃখ পাবে চেন্নাই");
        hashMap.put("DESCRIPTION", "চেন্নাই সুপার কিংসের হয়ে আর তিন ম্যাচ খেলার সুযোগ পাবেন মুস্তাফিজুর রহমান। বিসিবি থেকে ১ মে পর্যন্ত অনাপত্তিপত্র দেওয়া হয়েছে তাঁকে। এরপরই আইপিএল থেকে দেশে ফিরবেন মুস্তাফিজ।\n" +
                "\n" +
                "মুস্তাফিজের বিদায়ে দুঃখ পাবেন বলে জানিয়েছেন চেন্নাইয়ের ব্যাটিং কোচ মাইক হাসি। আজ ঘরের মাঠ চিপকে লখনউ সুপার জায়ান্টসের বিপক্ষে মাঠে নামবে চেন্নাই। এই ম্যাচের আগে সংবাদ সম্মেলনে আইপিএলের মাঝপথে মুস্তাফিজের দেশে ফিরে যাওয়া প্রসঙ্গে এক প্রশ্নে হাসি বলেন, 'তার (মুস্তাফিজ) স্লোয়ার বলটা অসাধারণ, এটা খেলা বেশ কঠিন, বিশেষ করে চেন্নাইয়ে। যখন সে চলে যাবে, আমরা দুঃখ পাব। কিন্তু তার দেশ তাকে ডাকছে। সে যত দিন থাকতে পারবে, তত দিন আমরা তাকে রাখতে চাই। আমরা তার পারফরম্যান্সে বেশ খুশি।'\n" +
                "আজ লখনউ ম্যাচের পর আরো দুইটি ম্যাচে চেন্নাই সুপার কিংসের সঙ্গে থাকবেন মুস্তাফিজ। ২৮ এপ্রিল সানরাইজার্স হায়দরাবাদ ও ১ মে কিংস ইলেভেন পাঞ্জাবের বিপক্ষে মাঠে নামবে চেন্নাই। এখন পর্যন্ত সাত ম্যাচ খেলে পয়েন্ট টেবিলের চার নম্বরে আছে আইপিএল ইতিহাসের সবচেয়ে সফল দলটি। চার জয়ের পিঠে তিন ম্যাচ হেরেছে চেন্নাই।\n" +
                "এক ম্যাচ ছাড়া সবগুলোতে একাদশে ছিলেন মুস্তাফিজ। এখন পর্যন্ত তাঁর শিকার ১১ উইকেট।");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://www.kalerkantho.com/_next/image?url=https%3A%2F%2Fcdn.kalerkantho.com%2Fpublic%2Fnews_images%2F2024%2F04%2F23%2F1713861723-a5b661131bdc6056bca96f5a41dfe120.jpg&w=828&q=100");
        hashMap.put("CAT", "summer");
        hashMap.put("TITLE", "প্রচণ্ড গরমে শিশুরা বেশি কাবু ৫ রোগে");
        hashMap.put("DESCRIPTION", "প্রচণ্ড গরমে অসুস্থ হয়ে পড়ছে অনেক শিশু। বেশির ভাগের সর্দি-জ্বর, শরীরে র\u200C্যাশ ওঠা, ডায়রিয়া, নিউমোনিয়া ও অ্যাজমার সমস্যা বেড়েছে। এই পাঁচ রোগে বেশি কাবু হচ্ছে শিশুরা। গতকাল সোমবার ঢাকার শিশু হাসপাতাল ঘুরে এসব তথ্য জানা গেছে। হাসপাতালের বহির্বিভাগে গতকাল চিকিৎসা নিতে আসা ৪০৬ জন রোগীর তথ্য বিশ্লেষণে দেখা গেছে, ৪৭ শতাংশ রোগী এসেছে সর্দি-জ্বর নিয়ে, ২৭ শতাংশের শরীরে র\u200C্যাশ ওঠা বা চর্মরোগের সমস্যা, ১৩ শতাংশের ডায়রিয়া, ৮ শতাংশের নিউমোনিয়া ও ৫ শতাংশের অ্যাজমার সমস্যা। রোগীরা সবচেয়ে বেশি ভর্তি হচ্ছে। কিন্তু হাসপাতালটির শয্যাসংকটের কারণে বেশির ভাগ রোগীকে প্রাথমিক চিকিৎসা দিয়ে সরকারি হাসপাতালে পাঠানো হচ্ছে। \n" +
                "\n" +
                "গতকাল সকাল ১০টার দিকে হাসপাতালটির জরুরি বিভাগে গিয়ে দেখা যায়, দেড় শতাধিক মানুষের ভিড়। টিকিট কাউন্টারের লাইনে আরো এক শ জনের মতো। এই ভিড়ের মধ্যে অনেকে ক্লান্তি নিয়ে মেঝেতে বসে আছেন। তাঁদের একজন তানিয়া আক্তার।  কোলে থাকা তিন মাসের অসুস্থ শিশুসন্তানকে নিয়ে তিনি বড় পেরেশানে আছেন। তানিয়া জানান, গত চার দিন ধরে ঠাণ্ডা-জ্বরে ভুগছে তাঁর শিশু। স্থানীয় ফার্মেসি থেকে ওষুধ কিনে খাওয়ালেও রোগ সারছে না। তাই চিকিৎসার জন্য গাজীপুর থেকে ভোরের ট্রেনে ঢাকা এসেছেন।\n" +
                "\n" +
                "তানিয়ার সঙ্গে যখন কথা হচ্ছে তখন পাশ থেকে আরেক নারী জানালেন, তাঁর দুই বছরের ছেলে চার দিন ধরে সর্দি-জ্বর ও কাশির সমস্যায় ভুগছে। কিছুতেই জ্বর কমছে না। এখানে একটি শয্যার জন্য গত দুই দিন ধরে তিনি অপেক্ষায় আছেন।\n" +
                "\n" +
                "হাসপাতালের পরিচালক অধ্যাপক ডা. জাহাঙ্গীর আলম কালের কণ্ঠকে বলেন, ‘টানা গরমে রোগীর চাপ অনেক বেড়েছে। ভর্তির প্রয়োজনও হচ্ছে অনেক বেশি। এই বাড়তি চাপ সামলাতে আমরা রোগীদের দ্রুত ছেড়ে দেওয়ার চেষ্টা করি। যেমন, নিউমোনিয়া রোগীদের ক্ষেত্রে আমরা সাত-আট দিন চিকিৎসা দিয়ে থাকি। পাঁচ দিনের মধ্যে অবস্থার উন্নতি হলে আমরা তাদের ছেড়ে দেওয়ার ব্যবস্থা করি। এটি পরিবারের জন্যও ভালো এবং অন্য নতুন রোগীও ভর্তি হতে পারে।’\n" +
                "\n" +
                "ডা. জাহাঙ্গীর আলম বলেন, ‘বর্তমানে প্রতিদিন গড়ে শতাধিক রোগীকে আমাদের ভর্তি করতে হচ্ছে। তাদের জন্য আসন খালি করতে অন্য ১০০ জনকে ছেড়ে দিতে হচ্ছে। এক মাসের শিশুদের ক্ষেত্রে কোনো লাইন যাতে না থাকে, সে ব্যবস্থা নেওয়া হয়েছে। এই গরমে তাদের অবস্থা খারাপ হয়ে যাবে।’\n" +
                "\n" +
                "তিনি বলেন, ‘সর্দি-জ্বর, র\u200C্যাশ ওঠা, চর্ম সমস্যা, ডায়রিয়া, নিউমোনিয়া ও অ্যাজমা—মূলত এই পাঁচ ধরনের রোগী চিকিৎসার জন্য বেশি আসছে। এর মধ্যে নিউমোনিয়ার রোগীদের ভর্তির প্রয়োজন হচ্ছে সবচেয়ে বেশি। গত ২৪ ঘণ্টায় ৩২ রোগীর মধ্যে ১৯ জনকে ভর্তি করতে হয়েছে। এ পর্যন্ত ৮৭ জন নিউমোনিয়ার রোগী হাসপাতালে ভর্তি আছে। তবে একটা ভালো দিক হলো, অন্যান্য বছরের তুলনায় ডায়রিয়া রোগী খুব কম। গতকাল ৫৩ জন রোগী আউটডোরে ডায়রিয়া সমস্যা নিয়ে আসে। তাদের একজনকে ভর্তি করা হয়েছে।’\n" +
                "\n" +
                "অধ্যাপক ডা. জাহাঙ্গীর আলম বলেন, শিশুর জ্বর, সর্দি, কাশি বা শ্বাসকষ্ট হলে খেয়াল করুন বুকের পাঁজরের নিচের অংশ ভেতর দিকে দেবে যাচ্ছে কি না অথবা শিশু দ্রুত শ্বাস নিচ্ছে কি না। এই লক্ষণগুলো থাকলে বুঝতে হবে শিশুর নিউমোনিয়া হয়েছে। আরো কিছু লক্ষণ দেখা দিতে পারে, যেমন বমি বমি ভাব, খাবারে অনীহা, নিস্তেজ হয়ে পড়া, শ্বাস-প্রশ্বাসের গতি বেড়ে যাওয়া। এমন পরিস্থিতিতে রোগীকে দ্রুত হাসপাতালে নিয়ে এসে চিকিৎসা করাতে হবে।");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2024-04%2F78470c20-54d7-413c-a0fa-acffbb1e9578%2Fus_congress_240424_01.jpg?auto=format%2Ccompress&fmt=webp&format=webp&w=640");
        hashMap.put("CAT", "ukrain");
        hashMap.put("TITLE", "মার্কিন কংগ্রেসে ইউক্রেইন, ইসরায়েল সহায়তা বিল পাস");
        hashMap.put("DESCRIPTION", "যুক্তরাষ্ট্রের কংগ্রেসে ৯৫ বিলিয়ন ডলারের একটি ব্যাপক বৈদেশিক সহায়তা প্যাকেজ সহজেই পাস হয়েছে। রাশিয়ার বাহিনীর অগ্রাভিযান ও কিইভের সামরিক সরবরাহের ঘাটতির মধ্যে এ বিলটি পাস হওয়ায় ফলে ইউক্রেইনে নতুন করে হাজার হাজার কোটি ডলারের মার্কিন সামরিক সহায়তা পাঠানোর পথ পরিষ্কার হল।\n" +
                "\n" +
                "মঙ্গলবার রাতে মার্কিন কংগ্রেসের উচ্চকক্ষ সেনেট চারটি বিলের মিলিত প্যাকেজটি ৭৯-১৮ ভোটে অনুমোদন পায়। এর আগে শনিবার কংগ্রেসের নিম্নকক্ষ প্রতিনিধি পরিষদে বিলটি পাস হয়েছিল।\n" +
                "\n" +
                "এই প্যাকেজের অধিকাংশই ইউক্রেইন, ইসরায়েল, তাইওয়ান ও যুক্তরাষ্ট্রের ভারত-প্রশান্ত মহাসাগরীয় অঞ্চলের অংশীদারদের জন্য বরাদ্দ সামরিক সহায়তা। \n" +
                "\n" +
                "সেনেটের অনুমোদন পাওয়ার পর এখন বিলটি স্বাক্ষরের জন্য প্রেসিডেন্ট জো বাইডেনের কাছে যাবে। বাইডেন জানিয়েছেন, তিনি বুধবারই বিলটিতে স্বাক্ষর করবেন। বাইডেনের স্বাক্ষরের মাধ্যমে বিলটি যুক্তরাষ্ট্রের আইনে পরিণত হবে।\n" +
                "\n" +
                "ইউক্রেইনের জন্য এই ‘গুরুত্বপূর্ণ’ সহায়তা অনুমোদন করায় দেশটির প্রেসিডেন্ট ভলোদিমির জেলেনস্কি মার্কিন আইনপ্রণেতাদের প্রতি কৃতজ্ঞতা প্রকাশ করেছেন। রয়টার্স জানিয়েছে, সহায়তা প্যাকেজটির সবচেয়ে বড় অংশ ৬১ বিলিয়ন ডলার যাবে ইউক্রেইনে; দ্বিতীয় অংশ ২৬ বিলিয়ন ডলার যাবে ইসরায়েল ও বিশ্বজুড়ে সংঘাতপূর্ণ অঞ্চলে বেসামরিকদের মানবিক সহায়তা প্রদানের জন্য আর তৃতীয় অংশ ৮ দশমিক ১২ বিলিয়ন ডলার পাঠানো হবে ‘কমিউনিস্ট চীনকে ঠেকানোর’ জন্য ভারত-প্রশান্ত মহাসাগরীয় অঞ্চলে। এই প্যাকেজের অংশ চতুর্থ আরেকটি বিলে চীনের নিয়ন্ত্রিত সামাজিক মাধ্যম অ্যাপ টিকটকের ওপরে সম্ভাব্য নিষেধাজ্ঞার বিষয়টি অন্তর্ভুক্ত আছে। পাশাপাশি এতে রাশিয়ার জব্দ করা সম্পদ ইউক্রেইনে পাঠানো ও ইরানের ওপর নতুন নিষেধাজ্ঞা আরোপের পদক্ষেপগুলো আছে।");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2023-06%2Ffd84e7de-401a-4a81-bc32-37ce6a0a7fd1%2Fhigh_court_supreme_court_080921_19.jpg?auto=format%2Ccompress&fmt=webp&format=webp&w=640");
        hashMap.put("CAT", "high court");
        hashMap.put("TITLE", "কক্সবাজারে ভোটার হওয়া রোহিঙ্গাদের তালিকা চায় হাই কোর্ট");
        hashMap.put("DESCRIPTION", "কক্সবাজার জেলায় কতজন রোহিঙ্গাকে ভোটার করা হয়েছে তার তালিকা চেয়েছে হাই কোর্ট।\n" +
                "\n" +
                "বুধবার একটি রিট আবেদনের শুনানি নিয়ে বিচারপতি নাইমা হায়দার ও বিচারপতি কাজী জিনাত হকের বেঞ্চ এ আদেশ দেয়।\n" +
                "\n" +
                "কক্সবাজার সদরের ঈদগাঁও ইউনিয়নের ভোটার মো. হামিদুর রহমানের পক্ষে সুপ্রিম কোর্টের আইনজীবী মোহাম্মদ ছিদ্দিক উল্লাহ মিয়া এ রিট আবেদন করেন। আদালতে তিনিই আবেদনের পক্ষে শুনানি করেন।\n" +
                "\n" +
                "ছিদ্দিক উল্লাহ মিয়া বিডিনিউজ টোয়েন্টিফোর ডটকমকে বলেন, কক্সবাজারে কতজন রোহিঙ্গা নাগরিকত্ব পেয়ে ভোটার হয়েছেন আবেদনে তার তালিকা দাখিলের নির্দেশনা চাওয়া হয়। একইসঙ্গে ভোটার তালিকা থেকে রোহিঙ্গাদের বাদ দেওয়ার আর্জি জানানো হয়। “কক্সবাজার সদর উপজেলার ঈদগাঁও ইউনিয়নের ভোটার তালিকায় অন্তর্ভুক্ত হয়ে বাংলাদেশের জাতীয় পরিচয়পত্র (এনআইডি) নিয়েছেন ৩৮ জন রোহিঙ্গা। রিটে তাদের তালিকা যুক্ত করা হয়েছে। এছাড়া কয়েকশ রোহিঙ্গা একই ইউনিয়নে নাগরিকত্ব নিয়েছেন।”\n" +
                "\n" +
                "এ আইনজীবী বলেন, শুনানি শেষে আদালত আগামী ৬ জুনের মধ্যে জেলায় রোহিঙ্গা ভোটারের তালিকা দাখিল করতে কক্সবাজারের ডিসিসহ সংশ্লিষ্টদের আদেশ দিয়েছে।\n" +
                "\n" +
                "স্থানীয় সরকার মন্ত্রণালয়ের সচিব, নির্বাচন কমিশন সচিব, কক্সবাজার জেলার জেলা প্রশাসক ও পুলিশ সুপারসহ সংশ্লিষ্টদের বিবাদী করা হয়েছে এ রিট মামলায়।");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2024-03%2Fc78f3259-8483-4615-af48-d81203cd2d4e%2Ffairuz_abontika_jnu_protest_180324_06.jpg?w=1200&auto=format%2Ccompress&fit=max");
        hashMap.put("CAT", "baby torture");
        hashMap.put("TITLE", "শিশুর উপর যৌন নির্যাতন বন্ধ হবে কবে?");
        hashMap.put("DESCRIPTION", "সংবাদমাধ্যমে প্রায়ই শিশুদের যৌন নির্যাতনের শিকার হওয়ার খবর দেখতে পাই।\n" +
                "\n" +
                "তবে আমার মনে হয় সাংবাদমাধ্যমে এই বিষয়ে যতটা সংবাদ প্রকাশ পায়, তার বাইরেও এমন অনেক ঘটনা ঘটে যা আড়ালেই থেকে যায়।\n" +
                "\n" +
                "আমি এক কিশোরীকে চিনি, যার বয়স ১৬ বছর। মৌলভীবাজার জেলার কুলাউড়া উপজেলায় তার বাড়ি।\n" +
                "\n" +
                "নার্সারীতে পড়াকালেই এই কিশোরী দূর সম্পর্কের চাচার দ্বারা যৌন নির্যাতনের শিকার হয়। বিষয়টি সে পরিবারকে জানালেও তারা এটিকে আড়াল করে।\n" +
                "\n" +
                "সপ্তম শ্রেণিতে ওঠার পর এই কিশোরীকে যৌন নির্যাতন করে তার নিজ বাবা ও চাচা।\n" +
                "\n" +
                "এই ঘটনা দুইটি সে তার পরিবারের অন্য সদস্যদের জানাতে পারেনি। কেননা সে দেখেছে,এর আগেও ওর পরিবার একই ধরণের ঘটনায় কোনো ব্যবস্থা নেয়নি।\n" +
                "\n" +
                "নিজের কথা বলতে গিয়ে সে আমাকে বলে, \"অনেক কন্যা সন্তান নিজের বাবার কাছেই নিরাপদ থাকে না, বাইরের মানুষ তো দূরের কথা।\"\n" +
                "\n" +
                "আমাদের সমাজে তার মতো অনেক শিশু কিশোরী নানা ভাবে যৌন নির্যাতনের শিকার হয়। যেসব ঘটনা আমাদের সমাজ ও পরিবার কেউই হয়ত জানে না।\n" +
                "\n" +
                "প্রতিবেদকের বয়স: ১৬। জেলা: মৌলভীবাজার।");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2024-04%2F90147c40-493e-4fd8-aa36-b484583fa418%2Fbagerhat_child_marriage_240424.jpg?w=1200&auto=format%2Ccompress&fit=max");
        hashMap.put("CAT", "child marriage");
        hashMap.put("TITLE", "বাল্যবিয়ে: শিশুর কোলে শিশু");
        hashMap.put("DESCRIPTION", "বাল্যবিয়ের পর ১৫ বছর বয়সেই শিশু সন্তানের জন্ম দেন বাগেরহাটের এক তরুণী। বর্তমানে তার বয়স ১৯ বছর।\n" +
                "\n" +
                "এই তরুণী বাগেরহাট সদর উপজেলার একটি গ্রামে বসবাস করেন।\n" +
                "\n" +
                "হ্যালো ডট বিডিনিউজ টোয়েন্টিফোর ডটকমকে তিনি জানান, বয়স কম থাকায় বাল্যবিয়ের কুফল সম্পর্কে জানা ছিল না তার। তাই বিয়েতে রাজি হন।\n" +
                "\n" +
                "অল্প বয়সে বিয়ের কারণে সংসার জীবন সম্পর্কে বুঝে উঠতেও সমস্যা হয়েছে। তৈরি হয়েছে নানা শারীরিক জটিলতাও।\n" +
                "\n" +
                "হ্যালোকে তিনি বলেন, “কম বয়সে বিয়ে হওয়ার কারণে আমার অনেক শারীরিক সমস্যা হয়। আমার থেকে আমার মা অনেক সুস্থ।”\n" +
                "\n" +
                "নিজের মেয়েকে কখনো বাল্যবিয়ে দেবেন না বলে জানান তিনি। মেয়েকে পড়াশোনা করানোর ইচ্ছে তার।\n" +
                "\n" +
                "প্রতিবেদকের বয়স: ১৩। জেলা: বাগেরহাট।");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2024-04%2F9a75be84-a5d2-40c8-9040-83d2e67d0792%2Funicef_meena_media_awards_220424_41.jpg?w=1200&auto=format%2Ccompress&fit=max");
        hashMap.put("CAT", "mina award");
        hashMap.put("TITLE", "১৫ সাংবাদিক পেলেন 'মীনা মিডিয়া অ্যাওয়ার্ডস'");
        hashMap.put("DESCRIPTION", "শিশুদের অধিকার বিষয়ক কাজের স্বীকৃতি দিতে এবছর ১৫ জন সাংবাদিককে পুরস্কৃত করেছে জাতিসংঘ শিশু তহবিল- ইউনিসেফ।\n" +
                "\n" +
                "সোমবার রাজধানীর হোটেল সোনারগাঁওয়ে বসে মীনা মিডিয়া অ্যাওয়ার্ডসের ১৮ তম আসর। এই আয়োজনে ১২ ক্যাটাগরিতে এই গণমাধ্যমকর্মীদের পুরস্কার দেওয়া হয়।\n" +
                "\n" +
                "বড়দের পাশাপাশি তিন জন শিশু সাংবাদিককেও পুরস্কৃত করা হয়েছে।\n" +
                "\n" +
                "অনুষ্ঠানের এই পর্বটি সঞ্চালনা করে হ্যালো ডট বিডিনিউজ টোয়েন্টিফোর ডটকমের শিশু সাংবাদিক কে. এম. ইফতেশাম ইসলাম ও আনিকা আকরাম অর্পি।\n" +
                "\n" +
                "অনুষ্ঠানে প্রধান অতিথি ছিলেন জাতীয় সংসদের স্পিকার ড. শিরীন শারমিন চৌধুরী।\n" +
                "\n" +
                "মীনা মিডিয়া অ্যাওয়ার্ডস বিজয়ীদের শুভেচ্ছা জানিয়ে তিনি বলেন, \"শিশুদের প্রয়োজনগুলো সবার সামনে তুলে ধরার ক্ষেত্রে গণমাধ্যমের গুরুত্বপূর্ণ ভূমিকা রয়েছে।\"\n" +
                "\n" +
                "অনুষ্ঠানে বিশেষ অতিথি ছিলেন শিক্ষাবিদ ড. জাফর ইকবাল, ডেইলি স্টার সম্পাদক মাহফুজ আনাম, বিটিভির মহাপরিচালক ড. মো. জাহাঙ্গীর আলম, ঢাকা ট্রিবিউনের নির্বাহী সম্পাদক রিয়াজ আহমেদ ও চলচ্চিত্র নির্মাতা শামীম আখতার।\n" +
                "\n" +
                "এছাড়াও উপস্থিত ছিলেন ইউনিসেফের জাতীয় শুভেচ্ছাদূত বিদ্যা সিনহা মীম ও ইউনিসেফের ইয়ুথ অ্যাডভোকেট রাবা খান।\n" +
                "\n" +
                "অনুষ্ঠানের শুরুতে বাংলাদেশে ইউনিসেফের প্রতিনিধি শেলডট ইয়েট বলেন, \"মীনা মিডিয়া অ্যাওয়ার্ডসের মাধ্যমে বিজয়ীদের সম্মান জানানোর এই সুবর্ণক্ষণে আমরা শিশুদের কথাগুলো শোনার, তাদের স্বপ্ন পূরণের সুযোগ করে দেওয়ার এবং তাদের অধিকারগুলো নিশ্চিত করার আমাদের যে অঙ্গীকার রয়েছে তা পুনর্ব্যক্ত করছি।\"\n" +
                "\n" +
                "এবার মীনা মিডিয়া অ্যাওয়ার্ডসের বিচারক ছিলেন ঢাকা বিশ্ববিদ্যালয়ের অধ্যাপক গীতি আরা নাসরিন, সহযোগী অধ্যাপক কাজলী শেহরীন ইসলাম, এএফপির ব্যুরো চিফ শফিকুল আলম, রয়টার্স বাংলাদেশের সাবেক ব্যুরো চিফ সিরাজুল ইসলাম কাদির, রয়টার্স বাংলাদেশের ব্যুরো চিফ রুমা পাল, রয়টার্স বাংলাদেশের মাল্টিমিডিয়া জার্নালিস্ট রফিকুর রহমান, আলোকচিত্রী নাসির আলী মামুন, আলিয়ঁস ফ্রঁসেজ দ্য ঢাকার আলোকচিত্রী ও প্রশিক্ষক আবির আবদুল্লাহ এবং আলোকচিত্রী জান্নাতুল মাওয়া।\n" +
                "\n" +
                "প্রতিবেদকের বয়স: ১০। জেলা: ঢাকা।");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("IMAGE", "https://media.assettype.com/bdnews24%2F2024-04%2F5fc416a2-79b4-4720-982f-3da7dd9c7979%2Frangpur_mahmudur_nut_seller_200424__1_.jpg?w=1200&auto=format%2Ccompress&fit=max");
        hashMap.put("CAT", "nuts entrepreneurship");
        hashMap.put("TITLE", "স্কুল ছেড়ে বাদাম বিক্রি করে শিশু");
        hashMap.put("DESCRIPTION", "সংসারে আর্থিক টানাপোড়েনে পড়াশোনা ছেড়ে স্বল্প পুঁজির ব্যবসায় নেমেছে রংপুরের এক শিশু। মাহমুদুর নামের এই শিশুটি রংপুর নগরীতে বাদাম বিক্রি করে।\n" +
                "\n" +
                "সে হ্যালো ডট বিডিনিউজ টোয়েন্টিফোর ডটকমকে জানায়, পঞ্চম শ্রেণির পর তার আর পড়াশোনা করা হয়নি। সংসারে আর্থিক টানাপোড়েনে তাকে বাধ্য হয়ে কাজে নামতে হয়েছে।\n" +
                "\n" +
                "মাহমুদুর বলছিল, “আমি দুই বছর ধরে বাদাম বিক্রি করি। এই কাজ করে আমার নিজের ও সংসারের খরচ চালাই।”\n" +
                "\n" +
                "সে জানায়, বাদাম বিক্রি করে রোজ তার চার থেকে পাঁচশ টাকা আয় হয়। নিজের আয়ের টাকায় ছোট ভাই বোনকে পড়াশোনা করানোর ইচ্ছা তার।\n" +
                "\n" +
                "প্রতিবেদকের বয়স: ১৫। জেলা: রংপুর।");
        arrayList.add(hashMap);





    }

    //ArrayList Close==============================//
    //ArrayList Close==============================//







    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Do you Want to Exit This app ?")
                .setMessage("News App")
                .setIcon(R.drawable.warning)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finishAffinity();
                        toast(MainActivity.this, "Exit");



                    }
                })


                .setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                })

                .show();



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adView != null) {
            adView.destroy();
        }
    }
}//MainActivity Close====================//