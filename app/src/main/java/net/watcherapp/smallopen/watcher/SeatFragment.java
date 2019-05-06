package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class SeatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seat_image, container, false);
        Bundle args = getArguments();
        String pcName= args.getString("pcName", "none");


        ImageView imageView = (ImageView) rootView.findViewById(R.id.seat_image);
        Picasso.get()
//                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                .load("http://nameyeowool.pythonanywhere.com/room/seat/"+pcName)
                .resize(400,300)
                .into(imageView);

        return rootView;
    }
}
