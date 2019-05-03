package net.watcherapp.smallopen.watcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lib.kingja.switchbutton.SwitchMultiButton;

import static java.lang.System.exit;


public class PcListFragment extends Fragment {

    public static final String LOG_TAG = "LOGNewsFragment";

    // protected TextView mTextView;
    public static final String QUEUE_TAG = "VolleyRequest";
//    JSONObject mResult = null;
//    ArrayList<PcListInfo> mList = new ArrayList<PcListInfo>();
//    protected NewsAdapter mAdapter = new NewsAdapter(mList);
//    SessionManager mSession = SessionManager.getsInstance(getContext());
//

//    private List<PojoOfJsonArray> myOptions = new ArrayList<>();
//    List<ListItem> consolidatedList = new ArrayList<>();

//    private RecyclerView mRecyclerView;
//    private Adapter adapter;

    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;
    private Adapter2 mAdapter2;

    JSONObject mResult = null;
    protected RequestQueue mQueue = null;

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mQueue = Volley.newRequestQueue(getContext());
        rootView = inflater.inflate(R.layout.fragment_pclist, container, false);

        // multi radio button
        SwitchMultiButton mSwitchMultiButton = (SwitchMultiButton) rootView.findViewById(R.id.switchmultibutton);
        mSwitchMultiButton.setText("혜화역", "성대역","회기역").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(tabText.equals("혜화역")){
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                    new PcListTask().execute("종로구");
                }else if(tabText.equals("성대역")){
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                    new PcListTask().execute("장안구");
                }else if(tabText.equals("회기역")) {
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
//                    new PcListTask().execute("종로구");

                }

            }
        });

        // recycler view
//        new PcListTask().execute();

        return rootView;
    }

    class PcListTask extends AsyncTask<String, String, String> {
        JSONObject res;
        JSONArray json_arr_contact;
        JSONArray json_arr_contact_non;
        List<PcListItem> consolidatedList = new ArrayList<>();
        @Override
        protected String doInBackground(String... strings) {


            try {
//                String ess = "http://nameyeowool.pythonanywhere.com/room/all/";
                String ess = "http://nameyeowool.pythonanywhere.com/room/"+strings[0]+"/";
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
//                Log.d("urlresponse", sb.toString());
                //handler.sendMessage(msg);
                //Log.d("woonse", sb.toString());
//                JSONArray jsonArray = new JSONObject(sb.toString()).getJSONArray("weather");        // json에 넣기
//                res= new JSONObject((sb.toString()));
//                Log.d("res",String.valueOf(res.optInt("cnt_all")));

//                JSONArray jsonArray = new JSONObject(sb.toString()).getJSONArray("contact");        // json에 넣기
//                JSONObject item = jsonArray.getJSONObject(0);
//                Log.d("contact_json", item.optString("name"));

                return sb.toString();

            }catch (Exception e){
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
            int cnt_contact = 1;
            int cnt_contact_non = 0 ;

            try {
                JSONObject jsonOb = new JSONObject(s);        // json에 넣기
                json_arr_contact = jsonOb.getJSONArray("contact");
                json_arr_contact_non = jsonOb.getJSONArray("contact_non");
                cnt_all = jsonOb.optInt("cnt_all");
                cnt_contact = jsonOb.optInt("cnt_contact");
                cnt_contact_non = jsonOb.optInt("cnt_non_contact");
                Log.d("json", String.valueOf(cnt_all));
                Log.d("json", String.valueOf(cnt_contact));
                Log.d("json", String.valueOf(cnt_contact_non));

                // first header
                SectionItem sectionFirstHeader = new SectionItem("Watcher 가맹점");
                consolidatedList.add(sectionFirstHeader);

                // get contact item
                // add
                for (int i = 0 ; i <  cnt_contact ; i ++) {
                    JSONObject jsonItem = json_arr_contact.getJSONObject(i);
                    ContactItem contactItem = new ContactItem(new PcInfoOfJson(jsonItem));
                    consolidatedList.add(contactItem);
                }

                // second header
                SectionItem sectionSecondHeader = new SectionItem("일반 지점");
                consolidatedList.add(sectionSecondHeader);

                // get non contact item
                // add
                for (int i = 0 ; i <  cnt_contact_non ; i ++) {
                    JSONObject jsonItem = json_arr_contact_non.getJSONObject(i);
                    ContactNonItem contactNonItem = new ContactNonItem(new PcInfoOfJson(jsonItem));
                    consolidatedList.add(contactNonItem);
                }

                Log.d("consolidated_list", String.valueOf(consolidatedList));

            }catch (JSONException e){
                Log.d("nojson",String.valueOf(e));
                Toast.makeText(getContext(),"error no json",Toast.LENGTH_LONG).show();
                exit(0);
            }

            mAdapter2 = new Adapter2(getContext(),consolidatedList);
            //adapter = new Adapter(getContext(), consolidatedList);
            Log.d("end","After madapter2 end");

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            Log.d("end", "After layoutManager ");
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter2);


        }
    }

//    protected void requestPcList() {
////        http://yeowool.pythonanywhere.com/room/all/
//        String url = "http://nameyeowool.pythonanywhere.com/room/all/";
//        //String url = "http://115.145.101.15/listnews.php";
//        Log.d(LOG_TAG, url);
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        mResult = response;
//
//                        Log.d(LOG_TAG, response.toString());
//                        Log.d("cnt_all",String.valueOf(mResult.optInt("cnt_all")));
//                        //mTextView.setText(response.toString());
//                        //drawList();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        request.setTag(QUEUE_TAG);
//        mQueue.add(request);
//    }



}
