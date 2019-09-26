package com.blogspot.yourfavoritekaisar.ems.NewsInet.Pengumuman.VIew;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.yourfavoritekaisar.ems.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengumumanFragment extends Fragment {


    public PengumumanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengumuman, container, false);
    }

}
