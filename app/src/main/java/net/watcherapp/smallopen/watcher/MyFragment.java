package net.watcherapp.smallopen.watcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("새로 만든 프래그먼트입니다.");

        return rootView;
    }
}
