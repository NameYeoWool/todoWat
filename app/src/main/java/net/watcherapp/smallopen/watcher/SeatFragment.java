package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class SeatFragment extends Fragment {
    private TextView nameView;
    private TextView noticeView;
    private TextView cntView;
    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seat_image, container, false);
        nameView = (TextView) rootView.findViewById(R.id.seat_pcName);
        noticeView = (TextView) rootView.findViewById(R.id.seat_notice);
        cntView = (TextView) rootView.findViewById(R.id.seat_cnt_textView);
        imageView = (ImageView) rootView.findViewById(R.id.seat_image);

        nameView.setText(pcName);

//        Bundle args = getArguments();
//        String pcName= args.getString("pcName", "");
//        String notice= args.getString("notice", "");

        mPcInfo = mRetrofitAPI.getPcInfo(pcName);
        mPcInfo.enqueue(new Callback<PcInfoOfJson>() {
            @Override
            public void onResponse(Call<PcInfoOfJson> call, Response<PcInfoOfJson> response) {
                noticeView.setText(response.body().getNotice());
                cntView.setText(String.valueOf(response.body().getCnt_empty()));
            }

            @Override
            public void onFailure(Call<PcInfoOfJson> call, Throwable t) {

            }
        });

        Picasso.get()
//                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                .resize(400,300)
                .into(imageView);

        return rootView;
    }
}
