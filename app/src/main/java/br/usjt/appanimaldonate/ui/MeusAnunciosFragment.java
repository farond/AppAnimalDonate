package br.usjt.appanimaldonate.ui;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalViewModel;

public class MeusAnunciosFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String MEUSANUNCIOS_FRAGMENT_TAG = "meusanuncios_fragment";
    private AnimalViewModel animalViewModel;
    private AnunciosAdapter adapter;
    private ProgressBar progressBar;

    private String mParam1;
    private String mParam2;

    public MeusAnunciosFragment() {
    }

    public static MeusAnunciosFragment newInstance(String param1, String param2) {
        MeusAnunciosFragment fragment = new MeusAnunciosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new AnunciosAdapter();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        animalViewModel.getAnimaisResponseLiveData().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animaisList) {
                if (animaisList != null) {
                    adapter.setResults(animaisList);
                }
                progressBar.setVisibility(View.GONE);
            }
        });

        adapter.setOnItemClickListener((position, animal) -> replaceFragment(R.id.frameLayout,
                NovoAnuncioFragment.newInstance("", animal),
                NovoAnuncioFragment.NOVOANUNCIO_FRAGMENT_TAG,
                "animal_click"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAnuncios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onResume(){
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        animalViewModel.getAnimais();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}
