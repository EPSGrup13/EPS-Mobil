package com.example.e_park;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentProfil extends Fragment {
    TextView textView;

    int veri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_profil,container,false);
        textView = viewRoot.findViewById(R.id.textView18);
        veri = this.getArguments().getInt("deger");
        textView.setText(String.valueOf(veri));
        return viewRoot;
    }
}
