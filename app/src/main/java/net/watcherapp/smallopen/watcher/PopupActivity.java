package net.watcherapp.smallopen.watcher;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;

public class PopupActivity extends AppCompatActivity {

    SharedPreferences sp;
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

//        getWindow().setLayout((int) (width*.8),(int)(height*.6));
        getWindow().setLayout((int) (width*.75),(int)(height*.545));
//        getWindow().setLayout(300,350);
        sp = getSharedPreferences("shared", MODE_PRIVATE);

//        imgView = (ImageView) findViewById(R.id.imageview);
//        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imgView);
//        photoViewAttacher.setScaleType(ImageView.ScaleType.CENTER_CROP);

        PhotoView photoview = (PhotoView)findViewById(R.id.events_photoview);

        try{

            Picasso.get()
//                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                    .load("http://www.watcherapp.net/events")
//                    .resize(300,300)
                    .into(photoview);


            Log.d("picasso ", "in");
        }catch(Exception e){
            Log.d("picasso error",String.valueOf(e));
        }
//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


//
//        Button btn = (Button) findViewById(R.id.btn_popup);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                long now = System.currentTimeMillis();
//                Date date = new Date(now);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String getTime = sdf.format(date);
//                Log.d("getTime",getTime);
//
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putBoolean("isShow",false);
//                editor.putString("today", getTime);
//                editor.commit();
//                finish();
//            }
//        });


    }

}
