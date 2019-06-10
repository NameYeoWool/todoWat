package net.watcherapp.smallopen.watcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class FoodListFragment extends Fragment {

    private RecyclerView mRecyclerView_food;
    private Adapter2 mAdapterFood;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("아직 지원하지 않습니다..");
//        return rootView;

        rootView = inflater.inflate(R.layout.fragment_foodlist, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //            Log.d("end", "After layoutManager ");
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView_food = (RecyclerView) rootView.findViewById(R.id.recyclerView_food);
        mRecyclerView_food.setLayoutManager(layoutManager);
        try {
            Log.d("foodList pcName", pcName);
            new FoodListTask().execute(pcName);
        } catch (Exception e) {
            Toast.makeText(getContext(), "인터넷을 확인해주셍", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    class FoodListTask extends AsyncTask<String, String, String> {
        JSONObject res;
        JSONArray json_food;
        JSONArray json_arr_contact_non;
        List<PcListItem> consolidatedList = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {


            try {
//                String ess = "http://nameyeowool.pythonanywhere.com/room/all/";
//                String ess = "http://nameyeowool.pythonanywhere.com/room/food/" + strings[0] + "/list/";
                String ess = "http://www.watcherapp.net/room/food/"+strings[0]+"/list";
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

            Log.d("foodList res",s);

            int cnt_all = 0;
            int cnt_food = 1;

            try{
                JSONObject jsonOb = new JSONObject(s);        // json에 넣기
                json_food = jsonOb.getJSONArray("food");

                cnt_all = jsonOb.optInt("cnt_all", 0);
                Log.d("cnt_all_food",String.valueOf(cnt_all));
                if( cnt_all != 0 ){
//                    // first header
//                    SectionItem sectionFirstHeader = new SectionItem("음식 순위");
//                    consolidatedList.add(sectionFirstHeader);
                    // get contact item
                    // add
                    for (int i = 0; i < cnt_all; i++) {
                        JSONObject jsonItem = json_food.getJSONObject(i);
                        FoodItem foodItem= new FoodItem(new FoodInfoOfJson(jsonItem));
                        Log.d("add consolidatedList", foodItem.getFoodInfoOfJson().getFoodName());
                        consolidatedList.add(foodItem);
                    }

                    mAdapterFood = new Adapter2(getContext(), consolidatedList);
                    mRecyclerView_food.setAdapter(mAdapterFood);

                }else{
                    Toast.makeText(getContext(), "cnt_all is 0", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e){
                //                Log.d("nojson",String.valueOf(e));
                Log.d("e",String.valueOf(e));
                Toast.makeText(getContext(), "음식 메뉴 업데이트 준비 중입니다.", Toast.LENGTH_LONG).show();
            }


//            mAdapterFood = new AdapterFood(getContext(), consolidatedList, new AdapterFood.OnItemClickListener() {
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
}
