package br.usjt.appanimaldonate.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Usuario;
import br.usjt.appanimaldonate.model.UsuarioViewModel;

public class ConfiguracaoFragment extends Fragment implements Validator.ValidationListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CONFIGURACAO_FRAGMENT_TAG = "configuracao_fragment";

    private Validator validator;

    private UsuarioViewModel usuarioViewModel;

    @NotEmpty(message = "Esse campo não pode ser vazio")
    private EditText editTextNome;

    @NotEmpty (message = "Esse campo não pode ser vazio")
    private EditText editTextCPF;

    @NotEmpty (message = "Preencha antes de continuar")// Indica que o campo anotado não pode ser vazio
    @Email(message = "Endereço de e-mail inválido")// Indica que o valor preenchido deve ser um email
    private EditText editTextEmail;

    @Password(message = "Senha inválida", min = 6, scheme = Password.Scheme.NUMERIC) // No password nós passamos 2 parametros , min e scheme , com o min estamos dizendo o número mínimo que nossa senha deve conter e scheme o padrão que nossa senha deve seguir, no nosso caso apenas números
    private EditText editTextSenha;

    @ConfirmPassword(message = "Os campos não batem") // valida o que o próprio nome já diz, confirma se este campo contém a mesma senha do campo anotado como @Password
    private EditText editTextConfirmaSenha;

    @NotEmpty (message = "Esse campo não pode ser vazio")
    private EditText editTextDataNascimento;
    private EditText editTextTelefone;
    private Usuario usuarioCorrente;
    private Button buttonSalvar;

    private boolean vemDoLogin;
    private String mParam2;

    public ConfiguracaoFragment() {
        // Required empty public constructor
    }



    public static ConfiguracaoFragment newInstance(boolean param1, String param2) {
        ConfiguracaoFragment fragment = new ConfiguracaoFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vemDoLogin = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        Hawk.init(getActivity()).build();

        validator = new Validator(this);
        validator.setValidationListener(this);
        usuarioCorrente = new Usuario();

        editTextNome = getView().findViewById(R.id.nomeEditText);
        editTextCPF = getView().findViewById(R.id.cpfEditText);
        editTextCPF.addTextChangedListener(Mask.insert(Mask.CPF_MASK, editTextCPF));
        editTextEmail = getView().findViewById(R.id.emailEditText);
        editTextSenha = getView().findViewById(R.id.senhaEditText);
        editTextConfirmaSenha = getView().findViewById(R.id.confirmaSenhaEditText);
        editTextDataNascimento = getView().findViewById(R.id.dataNascimentoEditText);
        editTextDataNascimento.addTextChangedListener(Mask.insert(Mask.DATANASC_MASK, editTextDataNascimento));
        editTextTelefone = getView().findViewById(R.id.telefoneUsuarioEditText);
        editTextTelefone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, editTextTelefone));
        buttonSalvar = getView().findViewById(R.id.salvarButton);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Log.d("CICLO_DE_VIDA","MainActivity --> onCreate");
        usuarioViewModel = new ViewModelProvider(getActivity()).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });
    }

    private void updateView(Usuario usuario) {
        if(usuario != null && usuario.getId() > 0){
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextCPF.setText(usuario.getCpf());
            editTextEmail.setText(usuario.getEmail());
            editTextSenha.setText(usuario.getSenha());
            editTextDataNascimento.setText(usuario.getDataNascimento());
            editTextTelefone.setText(usuario.getTelefone());
        }
    }

    public void salvar(){
        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setCpf(editTextCPF.getText().toString());
        usuarioCorrente.setEmail(editTextEmail.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioCorrente.setDataNascimento(editTextDataNascimento.getText().toString());
        usuarioCorrente.setTelefone(editTextTelefone.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Hawk.put("tem_cadastro",true);
        validator.validate();

    }


    @Override
    public void onValidationSucceeded() {
        Toast.makeText(getActivity(),"Usuário salvo com sucesso!",Toast.LENGTH_SHORT).show();
        if(vemDoLogin){
            getActivity().finish();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}