package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PcListFragment extends Fragment {

    public static final String LOG_TAG = "LOGNewsFragment";

    // protected TextView mTextView;
    public static final String QUEUE_TAG = "VolleyRequest";
    protected RequestQueue mQueue = null;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pclist, container, false);


        String[] sCheeseStrings= {"one","two","three","four","five"};

        //Your RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(getContext(),sCheeseStrings);

        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Watcher 가맹점"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"일반"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"Section 3"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"Section 4"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,"Section 5"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getContext(),R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));
        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);


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


//
//    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
//        ArrayList<PcListInfo> mArray = null;
//
//        public class ViewHolder extends RecyclerView.ViewHolder
//                implements View.OnClickListener{
//
//
//            //each dat item is just a string in this case
//            public TextView txtEmail;
//            public TextView txtMemo;
//            public TextView txtTime;
//
//            public ViewHolder(View root) {
//                super(root);
//                root.setOnClickListener(this);
//                txtEmail = (TextView) root.findViewById(R.id.txtEmail);
//                txtMemo = (TextView) root.findViewById(R.id.txtMemo);
//                txtTime = (TextView) root.findViewById(R.id.txtTime);
//            }
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),mArray.get(getAdapterPosition()).getAddress(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        public NewsAdapter(ArrayList<PcListInfo> list) {
//            mArray = list;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View root = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item, parent, false);
//            return new ViewHolder(root);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.txtEmail.setText(mArray.get(position).getName());
//            holder.txtMemo.setText(mArray.get(position).getAddress());
//            holder.txtTime.setText(mArray.get(position).getTime());
//        }
//
//
//
//        @Override
//        public int getItemCount() {
//            return mArray.size();
//        }
//    }
//
//
//    public void drawList() {
//        mList.clear();
//        try {
//            JSONArray items = mResult.getJSONArray("list");
//            Log.d(LOG_TAG, "draw");
//            for (int i = 0; i < items.length(); i++) {
//                JSONObject info = ((JSONObject) items.get(i));
//                String email = info.optString("name","none");
//                int newsid = info.getInt("newsid");
//                String memo = info.optString("address","noMemo");
//                String time = info.optString("time","noTime");
//
//                mList.add(new PcListInfo(email,newsid,memo,time));
//            }
//
//        } catch (JSONException | NullPointerException e) {
//            Toast.makeText(getContext(), "Error " + e.toString(), Toast.LENGTH_LONG).show();
//            mResult = null;
//
//        }
//
//        mAdapter.notifyDataSetChanged();
//    }
//
//
//    protected void requestNews() {
//        String url = mSession.getSERVER_ADDR() + "listnews.php";
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
//                        //mTextView.setText(response.toString());
//                        drawList();
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
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if( resultCode != RESULT_OK)
//            return;
//
//        switch (requestCode){
//            case 100:
//                String memo = data.getStringExtra("address");
//                writeNews(memo);
//                break;
//        }
//    }
//
//    protected  void writeNews(String memo){
//        String url = mSession.getSERVER_ADDR() + "writenews.php";
//        //String url = "https://192.168.100.195/writenews.php";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", mSession.getID());
//        params.put("address", memo);
//        JSONObject jsonObj = new JSONObject(params);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
//                url, jsonObj,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.i(LOG_TAG, "Response: " + response.toString());
//                        mResult = response;
//                        if(response.has("error")){
//                            try{
//                                        Toast.makeText(getContext(),response.getString("error").toString(),
//                                                Toast.LENGTH_LONG).show();
//                            }catch(JSONException e){
//                                e.printStackTrace();
//                            }
//
//                        }else
//                            requestNews();
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i(LOG_TAG, error.getMessage());
//                        Toast.makeText(getContext(), error.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//
//        request.setTag(QUEUE_TAG);
//        mQueue.add(request);
//
//    }//writeNews



}
