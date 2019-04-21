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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
    ArrayList<PcListInfo> mList = new ArrayList<PcListInfo>();

    JSONObject mResult = null;
    protected RequestQueue mQueue = null;

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mQueue = Volley.newRequestQueue(getContext());
        rootView = inflater.inflate(R.layout.fragment_pclist, container, false);

        new MyAsyncTask().execute();

        //get pcList
//        requestPcList();

//        String le = mResult.optString("cnt_contact","non");
//        Log.d("res",le);


//        try {
//            int cnt_contact= mResult.getInt("cnt_contact");
//            int cnt_contact =mResult.getInt("cnt_contact");
//
//            Log.d("json_cnt",String.valueOf(cnt_contact));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

////        int cnt_int = mResult.optInt("cnt_contact");
//        String[] sCheeseStrings= {"one","two","three","four","five"};
//
//        //Your RecyclerView
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
//
//        //Your RecyclerView.Adapter
//        mAdapter = new SimpleAdapter(getContext(),sCheeseStrings);
//
//        //This is the code to provide a sectioned list
//        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
//                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();
//
//        //Sections
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Watcher 가맹점"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"일반"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"Section 3"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"Section 4"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,"Section 5"));
//
//        //Add your adapter to the sectionAdapter
//        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
//        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
//                SimpleSectionedRecyclerViewAdapter(getContext(),R.layout.section,R.id.section_text,mAdapter);
//        mSectionedAdapter.setSections(sections.toArray(dummy));
//        //Apply this adapter to the RecyclerView
//        mRecyclerView.setAdapter(mSectionedAdapter);


//        RecyclerView r = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//
//        r.setAdapter(mAdapter);
//        r.setLayoutManager(new LinearLayoutManager(getContext()));
//        r.setItemAnimator(new DefaultItemAnimator());

//        mRecyclerView = rootView.findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
//
//        myOptions.add(new PojoOfJsonArray("name 1", "2016-06-21","address",3));
//        myOptions.add(new PojoOfJsonArray("name 2", "2016-06-05","address",3));
//        myOptions.add(new PojoOfJsonArray("name 2", "2016-06-05","address",3));
//        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17","address",3));
//        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17","address",3));
//        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17","address",3));
//        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17","address",3));
//        myOptions.add(new PojoOfJsonArray("name 2", "2016-06-05","address",3));
//        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17","address",3));
//
//        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = groupDataIntoHashMap(myOptions);
//
//        for (String date : groupedHashMap.keySet()) {
//            DateItem dateItem = new DateItem();
//            dateItem.setDate(date);
//            consolidatedList.add(dateItem);
//
//
//            for (PojoOfJsonArray pojoOfJsonArray : groupedHashMap.get(date)) {
//                GeneralItem generalItem = new GeneralItem();
//                generalItem.setPojoOfJsonArray(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
//                consolidatedList.add(generalItem);
//            }
//        }
//
//        adapter = new Adapter(getContext(), consolidatedList);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(adapter);

//        CookieHandler.setDefault(new CookieManager());

//        mQueue = mSession.getQueue();

//        requestNews();
        return rootView;
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {
        JSONObject res;
        JSONArray json_arr_contact;
        JSONArray json_arr_contact_non;
        List<PcListItem> consolidatedList = new ArrayList<>();
        @Override
        protected String doInBackground(String... voids) {


            try {
//                String ess="https://api.openweathermap.org/data/2.5/weather?lat=37.542&lon=126.986&appid=400c047eb6333f9fcba21f2c03564ea8&units=metric";
                String ess = "http://yeowool.pythonanywhere.com/room/all/";
                URL url = new URL(ess);
//            Log.d("woonse", url.getQuery());
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setConnectTimeout(5 * 1000);
                http.setReadTimeout(5 * 1000);
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
                StringBuffer sb = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                Log.d("urlresponse", sb.toString());
                //handler.sendMessage(msg);
                //Log.d("woonse", sb.toString());
//                JSONArray jsonArray = new JSONObject(sb.toString()).getJSONArray("weather");        // json에 넣기
//                res= new JSONObject((sb.toString()));
//                Log.d("res",String.valueOf(res.optInt("cnt_all")));

                JSONArray jsonArray = new JSONObject(sb.toString()).getJSONArray("contact");        // json에 넣기
                JSONObject item = jsonArray.getJSONObject(0);
                Log.d("contact_json", item.optString("name"));
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


                // get contact item
                // add
                for (int i = 0 ; i <  cnt_contact ; i ++) {
                    JSONObject jsonItem = json_arr_contact.getJSONObject(i);
                    ContactItem contactItem = new ContactItem(new PcInfoOfJson(jsonItem));
                    consolidatedList.add(contactItem);
                }

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
                System.exit(0);
            }

            for(int i = 0 ; i < cnt_contact; i++){
                Log.d("consolidated item", ((ContactItem)consolidatedList.get(i)).getPcInfoOfJson().getName());
            }
            mAdapter2 = new Adapter2(getContext(),consolidatedList);
            //adapter = new Adapter(getContext(), consolidatedList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter2);

//            String[] sCheeseStrings= {"one","two","three","four","five"};
//
//            //Your RecyclerView
//            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//            mRecyclerView.setHasFixedSize(true);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
//
//            //Your RecyclerView.Adapter
//            mAdapter = new SimpleAdapter(getContext(),sCheeseStrings);
//
//
//            //This is the code to provide a sectioned list
//            List<SimpleSectionedRecyclerViewAdapter.Section> sections =
//                    new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();
//
//            //Sections
//            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Watcher 가맹점"));
//            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(cnt_contact,"일반"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"Section 3"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"Section 4"));
////        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,"Section 5"));
//
//            //Add your adapter to the sectionAdapter
//            SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
//            SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
//                    SimpleSectionedRecyclerViewAdapter(getContext(),R.layout.section,R.id.section_text,mAdapter2);
//            mSectionedAdapter.setSections(sections.toArray(dummy));
//            //Apply this adapter to the RecyclerView
//            mRecyclerView.setAdapter(mSectionedAdapter);

        }
    }

    protected void requestPcList() {
//        http://yeowool.pythonanywhere.com/room/all/
        String url = "http://yeowool.pythonanywhere.com/room/all/";
        //String url = "http://115.145.101.15/listnews.php";
        Log.d(LOG_TAG, url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mResult = response;

                        Log.d(LOG_TAG, response.toString());
                        Log.d("cnt_all",String.valueOf(mResult.optInt("cnt_all")));
                        //mTextView.setText(response.toString());
                        //drawList();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        request.setTag(QUEUE_TAG);
        mQueue.add(request);
    }

    private HashMap<String, List<PojoOfJsonArray>> groupDataIntoHashMap(List<PojoOfJsonArray> listOfPojosOfJsonArray) {

        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = new HashMap<>();

        for (PojoOfJsonArray pojoOfJsonArray : listOfPojosOfJsonArray) {

            String hashMapKey = pojoOfJsonArray.getDate();

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<PojoOfJsonArray> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;
    }


    public class PcListInfo {
        String name;
        String address;
        Boolean contact;
        String noticce;
        String spec;

        public PcListInfo(String name,String address,Boolean contact, String notice, String spec) {
            this.name = name;
            this.address = address;
            this.contact = contact;
            this.spec = spec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Boolean getContact() {
            return contact;
        }

        public void setContact(Boolean contact) {
            this.contact = contact;
        }

        public String getNoticce() {
            return noticce;
        }

        public void setNoticce(String noticce) {
            this.noticce = noticce;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }
    }

}
