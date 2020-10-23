package br.usjt.appanimaldonate.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalViewModel;


public class NovoAnuncioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String NOVOANUNCIO_FRAGMENT_TAG = "novo anuncio_fragment";

    private EditText editTextNomeAnimal;
    private EditText editTextIdadeAnimal;
    private Spinner spinnerEspecieAnimal;
    private Spinner spinnerPorteAnimal;
    private Spinner spinnerRacaAnimal;
    private Spinner spinnerGeneroAnimal;
    private EditText editTextInformacaoVacAnimal;
    private Switch switchVacinacaoAnimal;
    private Switch switchCastracaoAnimal;
    private Animal novoAnuncioCorrente;
    private Button buttonSalvarAnuncio;
    private AnimalViewModel animalViewModel;
//    private Animal animalCorrente;

    private String mParam1;
    private Animal animal;

    public NovoAnuncioFragment() {
        // Required empty public constructor
    }

    public static NovoAnuncioFragment newInstance(String param1, Animal animal) {
        NovoAnuncioFragment fragment = new NovoAnuncioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, animal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            animal = (Animal) getArguments().getSerializable(ARG_PARAM2);
        }

        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        animalViewModel.getSalvoSucesso().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                String mensagem = "Criação de anuncio falhou!";
                if(sucesso){
                    mensagem = "Anuncio criado com sucesso!";
                }
                Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();
            }
        });
        animalViewModel.getAlteradoSucesso().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                String mensagem = "Alteração de anuncio falhou!";
                if(sucesso){
                    mensagem = "Anuncio alterado com sucesso!";
                }
                limparCampos();
                Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novo_anuncio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Hawk.init(getActivity()).build();

        novoAnuncioCorrente = new Animal();

        editTextNomeAnimal = getView().findViewById(R.id.nomeAnimalEditText);
        editTextIdadeAnimal = getView().findViewById(R.id.idadeAnimalEditText);
        editTextInformacaoVacAnimal = getView().findViewById(R.id.informacaoVacAnimalEditText);
        spinnerEspecieAnimal = getView().findViewById(R.id.especieAnimalSpinner);
        spinnerEspecieAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMESPECIESELECIONADO", "POSIÇÃO-->"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGeneroAnimal = getView().findViewById(R.id.generoAnimalSpinner);
        spinnerGeneroAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMGENEROSELECIONADO", "POSIÇÃO-->"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPorteAnimal = getView().findViewById(R.id.racaAnimalSpinner);
        spinnerPorteAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMPORTESELECIONADO", "POSIÇÃO-->"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRacaAnimal = getView().findViewById(R.id.porteAnimalSpinner);
        spinnerRacaAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMRACASELECIONADO", "POSIÇÃO-->"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        switchCastracaoAnimal = getView().findViewById(R.id.castracaoAnimalSwitch);
        switchVacinacaoAnimal = getView().findViewById(R.id.vacinacaoAnimalSwitch);
        buttonSalvarAnuncio = getView().findViewById(R.id.salvarAnuncioButton);

        buttonSalvarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        if(animal !=null){
            novoAnuncioCorrente = animal;
            editTextNomeAnimal.setText(novoAnuncioCorrente.getNomeAnimal());
            editTextIdadeAnimal.setText(novoAnuncioCorrente.getIdadeAnimal());
            editTextInformacaoVacAnimal.setText(novoAnuncioCorrente.getInformacao());
            spinnerRacaAnimal.setSelection(((ArrayAdapter)spinnerRacaAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getRacaAnimal()));
            spinnerPorteAnimal.setSelection(((ArrayAdapter)spinnerPorteAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getPorteAnimal()));
            spinnerEspecieAnimal.setSelection(((ArrayAdapter)spinnerEspecieAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getEspecieAnimal()));
            spinnerGeneroAnimal.setSelection(((ArrayAdapter)spinnerGeneroAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getGeneroAnimal()));
//            spinnerRacaAnimal.setSelection(((ArrayAdapter)spinnerRacaAnimal.getAdapter()).getPosition(animalCorrente.getRacaAnimal()));
            switchVacinacaoAnimal.setChecked(novoAnuncioCorrente.isVacina());
            switchCastracaoAnimal.setChecked(novoAnuncioCorrente.isCastrado());
        }

    }

    public void salvar(){
        novoAnuncioCorrente.setNomeAnimal(editTextNomeAnimal.getText().toString());
        novoAnuncioCorrente.setIdadeAnimal(editTextIdadeAnimal.getText().toString());
        novoAnuncioCorrente.setInformacao(editTextInformacaoVacAnimal.getText().toString());
        novoAnuncioCorrente.setEspecieAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setPorteAnimal(spinnerPorteAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setRacaAnimal(spinnerRacaAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setGeneroAnimal(spinnerGeneroAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setVacina(switchVacinacaoAnimal.isChecked());
        novoAnuncioCorrente.setCastrado(switchCastracaoAnimal.isChecked());
        if(animal == null){
            animalViewModel.salvarAnimal(novoAnuncioCorrente);
        }else{
            Log.d("ANIMALKP","alterar");
            animalViewModel.alterarAnimal(novoAnuncioCorrente);
        }
        limparCampos();
    }

    private void limparCampos(){
        editTextNomeAnimal.setText("");
        editTextIdadeAnimal.setText("");
        editTextInformacaoVacAnimal.setText("");
        spinnerPorteAnimal.setSelection(0);
        spinnerRacaAnimal.setSelection(0);
        spinnerEspecieAnimal.setSelection(0);
        spinnerGeneroAnimal.setSelection(0);
    }
}