package com.watayouxiang.widgetlibdemo.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.watayouxiang.widgetlibdemo.R;

public class TestFragment extends Fragment {
    static TestFragment getInstance(String name) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("_name", name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout, container, false);
        TextView tv_name = root.findViewById(R.id.tv_name);
        tv_name.setText(getName());
        return root;
    }

    private String getName() {
        String name = "";
        Bundle arguments = getArguments();
        if (arguments != null) {
            name = arguments.getString("_name", "");
        }
        return name;
    }
}
