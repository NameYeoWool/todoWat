package net.watcherapp.smallopen.watcher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import lib.kingja.switchbutton.SwitchMultiButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.exit;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;

public class PcMapFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private MapView mapView = null;
    private GoogleMap mGoogleMap = null;

    private View bottomSheet;
    private TextView txV_pcname;
    private TextView txV_address;
    private TextView txV_cnt;
    private Button btn_info;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView)rootView.findViewById(R.id.map);
        mapView.getMapAsync(this);

        bottomSheet = rootView.findViewById(R.id.bottom_view);
        txV_pcname = (TextView) bottomSheet.findViewById(R.id.bottom_name);
        txV_address = (TextView) bottomSheet.findViewById(R.id.bottom_address);
        txV_cnt = (TextView) bottomSheet.findViewById(R.id.bottom_cnt);
        btn_info = (Button) bottomSheet.findViewById(R.id.bottom_btn_info);


        SwitchMultiButton mSwitchMultiButton = (SwitchMultiButton) rootView.findViewById(R.id.map_switchmultibutton);
        mSwitchMultiButton.setText("혜화역", "성대역","회기역").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(mGoogleMap == null) {
                    Toast.makeText(getContext(), "잠시 후 다시 눌러주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (tabText.equals("혜화역")){
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                    new MapMarkerTask().execute("종로구");
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.582301,127.001849), 17));
                }else if(tabText.equals("성대역")){
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                    new MapMarkerTask().execute("장안구");
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.300170,126.970716),17));
                }else if(tabText.equals("회기역")) {
                    Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                    new MapMarkerTask().execute("회기동");
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.582301,127.001849),17));
                }


                mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {
                        PcInfoOfJson mPcInfoOfJson;
                        if ( marker.getTag() == null) {
                            Toast.makeText(getContext(),"가맹점이 아닙니다",Toast.LENGTH_SHORT).show();
                            bottomSheet.setVisibility(View.GONE);
                            return false;
                        }else {
                            Log.d("marker Title", marker.getTitle());
                            marker.showInfoWindow();
                            mPcInfo = mRetrofitAPI.getPcInfo(String.valueOf(marker.getTitle()));
                            mPcInfo.enqueue(new Callback<PcInfoOfJson>() {
                                @Override
                                public void onResponse(Call<PcInfoOfJson> call, Response<PcInfoOfJson> response) {
                                    Toast.makeText(getContext(),response.body().getAddress(),Toast.LENGTH_SHORT).show();
                                    PcInfoOfJson result = response.body();
                                    bottomSheet.setVisibility(View.VISIBLE);
                                    txV_pcname.setText(result.getName());
                                    txV_address.setText(result.getAddress());
                                    txV_cnt.setText(String.valueOf(result.getCnt_empty()));

                                    btn_info.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getContext(), PcinfoActivity.class);
                                            intent.putExtra("pcName", marker.getTitle());
                                            startActivity(intent);
                                        }
                                    });

                                }

                                @Override
                                public void onFailure(Call<PcInfoOfJson> call, Throwable t) {

                                }
                            });
                        }

                        return true;
                    }
                });

                mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        bottomSheet.setVisibility(View.GONE);
                    }
                });
            }
        });



        return rootView;
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    class MapMarkerTask extends AsyncTask<String, String, String> {
        JSONArray json_arr_contact;
        JSONArray json_arr_contact_non;
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

                // marker ( png file )
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.marker);
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 120, 120, false);

                // get contact item
                // add
                for (int i = 0 ; i <  cnt_contact ; i ++) {
                    JSONObject jsonItem = json_arr_contact.getJSONObject(i);
                    ContactItem contactItem = new ContactItem(new PcInfoOfJson(jsonItem));

//                    MarkerOptions makerOptions = new MarkerOptions();
//                    makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
//                            .position(new LatLng(contactItem.getPcInfoOfJson().getLatitude(),
//                                    contactItem.getPcInfoOfJson().getLongitude()))
//                            .title(contactItem.getPcInfoOfJson().getName()) // 타이틀.
//                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));


                    // 2. 마커 생성 (마커를 나타냄)
                    Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(contactItem.getPcInfoOfJson().getLatitude(),contactItem.getPcInfoOfJson().getLongitude()))
                            .title(contactItem.getPcInfoOfJson().getName()) // 타이틀.
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                    marker.setTag(new LatLng(contactItem.getPcInfoOfJson().getLatitude(),contactItem.getPcInfoOfJson().getLongitude()));
                }


                // get non contact item
                // add
                for (int i = 0 ; i <  cnt_contact_non ; i ++) {
                    JSONObject jsonItem = json_arr_contact_non.getJSONObject(i);
                    ContactNonItem contactNonItem = new ContactNonItem(new PcInfoOfJson(jsonItem));

                    MarkerOptions makerOptions = new MarkerOptions();
                    makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                            .position(new LatLng(contactNonItem.getPcInfoOfJson().getLatitude(),
                                    contactNonItem.getPcInfoOfJson().getLongitude()))
                            .title(contactNonItem.getPcInfoOfJson().getName()) // 타이틀.
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.unmarker));

                    // 2. 마커 생성 (마커를 나타냄)
                    mGoogleMap.addMarker(makerOptions);

                }


            }catch (JSONException e){
                Log.d("nojson",String.valueOf(e));
                Toast.makeText(getContext(),"error no json",Toast.LENGTH_LONG).show();
                exit(0);
            }


        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        LatLng SEOUL = new LatLng(37.582301, 127.001849);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("혜화");
        markerOptions.snippet("수도");
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 17));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(30));
    }



}
