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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NovoAnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    private Switch switchVacinacaoAnimal;
    private EditText editTextInformacaoVacAnimal;
    private Switch switchCastracaoAnimal;
    private Animal novoAnuncioCorrente;
    private Button buttonSalvarAnuncio;
    private AnimalViewModel animalViewModel;

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

            Spinner spinner = (Spinner) spinnerEspecieAnimal.findViewById(R.id.especieAnimalSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.especie_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner.setAdapter(adapter);
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

    private void limparCampos(){
        editTextNomeAnimal.setText("");
        editTextIdadeAnimal.setText("");
        editTextInformacaoVacAnimal.setText("");
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
        spinnerEspecieAnimal = getView().findViewById(R.id.especieAnimalSpinner);
        spinnerGeneroAnimal = getView().findViewById(R.id.generoAnimalSpinner);
        spinnerPorteAnimal = getView().findViewById(R.id.racaAnimalSpinner);
        spinnerRacaAnimal = getView().findViewById(R.id.porteAnimalSpinner);
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
            novoAnuncioCorrente.setInformacao(editTextInformacaoVacAnimal.getText().toString());
            novoAnuncioCorrente.setEspecieAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
            novoAnuncioCorrente.setPorteAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
            novoAnuncioCorrente.setRacaAnimal(spinnerRacaAnimal.getSelectedItem().toString());
        }

    }

    public void salvar(){
        novoAnuncioCorrente.setNomeAnimal(editTextNomeAnimal.getText().toString());
        novoAnuncioCorrente.setIdadeAnimal(editTextIdadeAnimal.getText().toString());
        novoAnuncioCorrente.setInformacao(editTextInformacaoVacAnimal.getText().toString());
        novoAnuncioCorrente.setEspecieAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setPorteAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setRacaAnimal(spinnerRacaAnimal.getSelectedItem().toString());
        if(animal == null){
            animalViewModel.salvarAnimal(novoAnuncioCorrente);
        }else{
            Log.d("ANIMALKP","alterar");
            animalViewModel.alterarAnimal(novoAnuncioCorrente);
        }

    }
}