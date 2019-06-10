package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.cnt_empty;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mPcInfo;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.mRetrofitAPI;
import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;


public class SeatFragment extends Fragment {
    private TextView nameView;
    private TextView cntView;
    private ImageView imageView;
    private ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seat_image, container, false);
        nameView = (TextView) rootView.findViewById(R.id.seat_pcName);
        cntView = (TextView) rootView.findViewById(R.id.seat_cnt_textView);
        imageView = (ImageView) rootView.findViewById(R.id.seat_image);
        imageButton = (ImageButton) rootView.findViewById(R.id.btn_refresh);

        nameView.setText(pcName);
        cntView.setText(cnt_empty);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get()
//                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                        .load("http://www.watcherapp.net/room/seat/"+pcName)
                        .resize(400,300)
                        .into(imageView);
                Toast.makeText(getContext(),"좌석 현황을 업데이트 합니다.",Toast.LENGTH_SHORT).show();
            }
        });


        Picasso.get()
//                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                .load("http://www.watcherapp.net/room/seat/"+pcName)
                .resize(400,300)
                .into(imageView);

        return rootView;
    }
}
