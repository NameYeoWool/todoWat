package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class PcSpecFragment extends Fragment {
    TextView txV_cpu;
    TextView txV_ram;
    TextView txV_graphicCard;
    TextView txV_monitor;
    TextView txV_headset;
    TextView txV_keyboard;
    TextView txV_mouse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pcspec, container, false);

        txV_cpu = (TextView) rootView.findViewById(R.id.spec_cpu);
        txV_ram = (TextView) rootView.findViewById(R.id.spec_ram);
        txV_graphicCard = (TextView) rootView.findViewById(R.id.spec_graphic);
        txV_monitor = (TextView) rootView.findViewById(R.id.spec_monitor);
        txV_headset = (TextView) rootView.findViewById(R.id.spec_headset);
        txV_keyboard = (TextView) rootView.findViewById(R.id.spec_keyboard);
        txV_mouse = (TextView) rootView.findViewById(R.id.spec_mouse);

//        Bundle args = getArguments();
//        String pcName= args.getString("pcName", "");
//        String notice= args.getString("notice", "");
//
        mPcInfo = mRetrofitAPI.getPcInfo(pcName);
        mPcInfo.enqueue(new Callback<PcInfoOfJson>() {
            @Override
            public void onResponse(Call<PcInfoOfJson> call, Response<PcInfoOfJson> response) {
                PcInfoOfJson result = response.body();
                String spec = "{spec : " + result.getSpec() + "}";
                try {
                    JSONObject job = new JSONObject(spec);
                    JSONArray jsonArray = job.getJSONArray("spec");
                    txV_cpu.setText(jsonArray.getJSONObject(0).optString("text",""));
                    txV_graphicCard.setText(jsonArray.getJSONObject(1).optString("text",""));
                    txV_ram.setText(jsonArray.getJSONObject(2).optString("text",""));
                    txV_monitor.setText(jsonArray.getJSONObject(3).optString("text",""));
                    txV_headset.setText(jsonArray.getJSONObject(4).optString("text",""));
                    txV_keyboard.setText(jsonArray.getJSONObject(5).optString("text",""));
                    txV_mouse.setText(jsonArray.getJSONObject(6).optString("text",""));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<PcInfoOfJson> call, Throwable t) {

            }
        });

        return rootView;
    }
}
