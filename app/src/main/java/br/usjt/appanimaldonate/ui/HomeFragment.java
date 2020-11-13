package br.usjt.appanimaldonate.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalViewModel;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String HOME_FRAGMENT_TAG = "home_fragment";
    private AnimalViewModel animalViewModel;
    private List<Animal> animais;
    private TextView conteudo;
    private Button buttonAtualizar;
    private AnimalAdapter adapter;
    private ProgressBar progressBar;

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
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

        adapter = new AnimalAdapter();

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

        adapter.setOnItemClickListener(new AnimalAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, Animal animal) {
                if (appInstalledOrNot("com.whatsapp")){
                    String message = "Olá ! Eu me chamo "+Hawk.get("nome_usuario")+", telefone:"+Hawk.get("telefone_usuario")
                            +" e gostaria de saber mais informações sobre o animal: "+animal.getNomeAnimal();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+55"+ animal.getUsuarioTelefone() + "&text="+message));
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "Whatsapp não está intalado neste aparelho", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =getActivity().getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
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