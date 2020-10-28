package br.usjt.appanimaldonate.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.usjt.appanimaldonate.R;

public class AnunciosFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ANUNCIOS_FRAGMENT_TAG = "anuncios_fragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnunciosFragment() {
        // Required empty public constructor
    }


    public static AnunciosFragment newInstance(String param1, String param2) {
        AnunciosFragment fragment = new AnunciosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anuncios, container, false);
    }
}