package net.watcherapp.smallopen.watcher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.Request.Method.POST;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;

public class ReviewActivity extends AppCompatActivity {

    private EditText edit_title;
    private EditText edit_content;
    private RatingBar review_reatingBar;
    private final OkHttpClient client = new OkHttpClient();
    private String title;
    private String content;
    private String rating;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( ev );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);



        edit_title = findViewById(R.id.editText_title);
        edit_content = findViewById(R.id.editText_content);



        Button btn_register = (Button) findViewById(R.id.btn_review_register);
        Button btn_cancle = (Button) findViewById(R.id.btn_review_cancle);
        review_reatingBar = (RatingBar) findViewById(R.id.review_activity_ratingbar);

        btn_cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edit_title.getText().toString();
                content = edit_content.getText().toString();
                rating = String.valueOf(review_reatingBar.getRating());

                new ReviewTask().execute();

            }
        });

    }

    class ReviewTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                String ess = "http://www.watcherapp.net/room/review/new/"+pcName + "/" + title + "/" + content + "/" + rating + "/";
                Log.d("review ess",ess);
                URL url = new URL(ess);
                HttpURLConnection client = (HttpURLConnection) url.openConnection();

                Log.d("review" , client.getRequestMethod());
                Log.d("review",client.getResponseMessage());

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
                StringBuffer sb = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {

            Intent intent = new Intent();
            setResult(1002,intent);
            finish();

        }
    }


}
