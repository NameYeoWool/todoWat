package net.watcherapp.smallopen.watcher;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class ReviewFragment extends Fragment {

    private RecyclerView mRecyclerView_review;
    private AdapterReview mAdapterReview;
    private View rootView;
    private RatingBar ratingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_review, container, false);

        ratingBar = (RatingBar) rootView.findViewById(R.id.review_ratingbar);

        FloatingActionButton btn_float = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton2);
        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ReviewActivity.class);
                startActivityForResult(intent,1001);
            }
        });





        return rootView;
    }

        class ReviewListTask extends AsyncTask<String, String, String> {
            JSONObject res;
            JSONArray json_reviews;
            JSONArray json_arr_contact_non;
            List<PcListItem> consolidatedList = new ArrayList<>();

            @Override
            protected String doInBackground(String... strings) {


                try {
//                String ess = "http://nameyeowool.pythonanywhere.com/room/all/";
//                String ess = "http://nameyeowool.pythonanywhere.com/room/food/" + strings[0] + "/list/";
                    String ess = "http://www.watcherapp.net/room/review/" + strings[0] + "/list/";
                    Log.d("url", ess);
                    URL url = new URL(ess);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setConnectTimeout(5 * 1000);
                    http.setReadTimeout(5 * 1000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
                    StringBuffer sb = new StringBuffer();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        sb.append(inputLine);
                    }

                    return sb.toString();

                } catch (Exception e) {
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

                int cnt_all = 0;
                int cnt_food = 1;

                try {
                    JSONObject jsonOb = new JSONObject(s);        // json에 넣기
                    Float rating_mean = (float) jsonOb.optDouble("rating_mean");
                    ratingBar.setRating(rating_mean);
                    json_reviews = jsonOb.getJSONArray("reviews");
                    cnt_all = jsonOb.optInt("cnt_all", 0);

                    if (cnt_all != 0) {
                        // add
                        for (int i = 0; i < cnt_all; i++) {
                            JSONObject jsonItem = json_reviews.getJSONObject(i);
                            ReviewItem reviewItem = new ReviewItem(new ReviewOfJson(jsonItem));
                            consolidatedList.add(reviewItem);
                        }

                        mAdapterReview = new AdapterReview(getContext(), consolidatedList);
                        mRecyclerView_review.setAdapter(mAdapterReview);

                    } else {
                        Toast.makeText(getContext(), "cnt_all is 0", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    //                Log.d("nojson",String.valueOf(e));
                    Log.d("e", String.valueOf(e));
                    Toast.makeText(getContext(), "음식 메뉴 업데이트 준비 중입니다.", Toast.LENGTH_LONG).show();
                }


//            mAdapterReview = new AdapterFood(getContext(), consolidatedList, new AdapterFood.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(FoodInfoOfJson item) {
//                        if (item == null) {
//                            Toast.makeText(getContext(), "아직 기능을 지원하지 않습니다..", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), item.getFoodName(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        }


    @Override
    public void onResume() {
        super.onResume();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //            Log.d("end", "After layoutManager ");
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView_review = (RecyclerView) rootView.findViewById(R.id.recyclerView_review);
        mRecyclerView_review.setLayoutManager(layoutManager);
        try {
            new ReviewListTask().execute(pcName);
        } catch (Exception e) {
            Toast.makeText(getContext(), "인터넷을 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}

