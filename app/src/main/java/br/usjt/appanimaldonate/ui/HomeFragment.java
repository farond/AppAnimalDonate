package br.usjt.appanimaldonate.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalViewModel;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String HOME_FRAGMENT_TAG = "home_fragment";
    private AnimalViewModel animaisViewModel;
    private List<Animal> animais;
    private TextView conteudo;
    private Button buttonAtualizar;
    private AnimalAdapter adapter;


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        animaisViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        animaisViewModel.getAnimaisResponseLiveData().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animalList) {
                if (animalList != null) {
                    adapter.setResults(animaisViewModel);
                }
            }
        });

        adapter.setOnItemClickListener(new AnimalAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, animal animal) {
                replaceFragment(R.id.frameLayoutMainActivity,
                        NovoAnuncioFragment.newInstance("",animal),
                        NovoAnuncioFragment.NOVOANUNCIO_FRAGMENT_TAG,
                        "animal_click");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}