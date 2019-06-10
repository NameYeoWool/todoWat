package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class EventsFragment extends Fragment {
    private TextView contentView;
    private PhotoView content_image_view;
    private ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        contentView = (TextView) rootView.findViewById(R.id.events_content);
//        Bundle args = getArguments();
//        String pcName= args.getString("pcName", "");
//        String notice= args.getString("notice", "");

        mPcInfo = mRetrofitAPI.getPcInfo(pcName);
        mPcInfo.enqueue(new Callback<PcInfoOfJson>() {
            @Override
            public void onResponse(Call<PcInfoOfJson> call, Response<PcInfoOfJson> response) {
                String notice = response.body().getNotice();
                Log.d("EventsFragemnt",notice);
                contentView.setText(notice);
            }

            @Override
            public void onFailure(Call<PcInfoOfJson> call, Throwable t) {

            }
        });

//        Picasso.get()
////                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
//                .load("http://www.watcherapp.net/room/seat/"+pcName)
////                .resize(400,300)
//                .into(content_image_view);

        return rootView;
    }
}
