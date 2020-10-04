package br.usjt.appanimaldonate.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class CadastroUsuarioActivity extends AppCompatActivity{

    /*private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    private TextView locationTextView;
    private double latitudeAtual;
    private double longitudeAtual;*/

    /*private Validator validator;

    private UsuarioViewModel usuarioViewModel;

    @NotEmpty (message = "Esse campo não pode ser vazio")
    private EditText editTextNome;

    @NotEmpty (message = "Esse campo não pode ser vazio")
    private EditText editTextCPF;

    @NotEmpty (message = "Preencha antes de continuar")// Indica que o campo anotado não pode ser vazio
    @Email (message = "Endereço de e-mail inválido")// Indica que o valor preenchido deve ser um email
    private EditText editTextEmail;

    @Password(message = "Senha inválida", min = 6, scheme = Password.Scheme.NUMERIC) // No password nós passamos 2 parametros , min e scheme , com o min estamos dizendo o número mínimo que nossa senha deve conter e scheme o padrão que nossa senha deve seguir, no nosso caso apenas números
    private EditText editTextSenha;

    @ConfirmPassword (message = "Os campos não batem") // valida o que o próprio nome já diz, confirma se este campo contém a mesma senha do campo anotado como @Password
    private EditText editTextConfirmaSenha;

    @NotEmpty (message = "Esse campo não pode ser vazio")
    private EditText editTextDataNascimento;
    private Usuario usuarioCorrente;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        replaceFragment(R.id.frameLayoutCadastroUsuarioActivity,
                ConfiguracaoFragment.newInstance(true,""),
                ConfiguracaoFragment.CONFIGURACAO_FRAGMENT_TAG,
                "home");

        /*Hawk.init(this).build();

        validator = new Validator(this);
        validator.setValidationListener(this);


        usuarioCorrente = new Usuario();

        editTextNome = (EditText)findViewById(R.id.nomeEditText);
        editTextCPF = (EditText)findViewById(R.id.cpfEditText);
        editTextCPF.addTextChangedListener(Mask.insert(Mask.CPF_MASK, editTextCPF));
        editTextEmail = (EditText)findViewById(R.id.emailEditText);
        editTextSenha = (EditText)findViewById(R.id.senhaEditText);
        editTextConfirmaSenha = (EditText)findViewById(R.id.confirmaSenhaEditText);
        editTextDataNascimento = (EditText)findViewById(R.id.dataNascimentoEditText);
        editTextDataNascimento.addTextChangedListener(Mask.insert(Mask.DATANASC_MASK, editTextDataNascimento));

        Log.d("CICLO_DE_VIDA", "SegundaActivity --> onCreate");
        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);


        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }*/

        }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }


        /*locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                latitudeAtual = lat;
                longitudeAtual = lon;
                locationTextView.setText(String.format("Lat: %f, Long: %f", lat, lon));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle
                    extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

        };
        //locationTextView = findViewById(R.id.locationTextView);
    }*/

 /*   private void updateView(Usuario usuario){
        if(usuario != null && usuario.getId() > 0){
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextCPF.setText(usuario.getCpf());
            editTextEmail.setText(usuario.getEmail());
            editTextSenha.setText(usuario.getSenha());
            editTextDataNascimento.setText(usuario.getDataNascimento());
        }
    }

    public void cadastrar(View view){
        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setCpf(editTextCPF.getText().toString());
        usuarioCorrente.setEmail(editTextEmail.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioCorrente.setDataNascimento(editTextDataNascimento.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Hawk.put("tem_cadastro",true);
        validator.validate();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onStart");

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS){
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locationListener);
                }
            }
            else{

                Toast.makeText(this, getString(R.string.semgps),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        // a validação foi efetuada, siga em frente
        Toast.makeText(this,"Usuário salvo com sucesso!",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }*/

 @Override
 protected void onStart(){
     super.onStart();
     Log.d("CICLO_DE_VIDA","MainActivity --> onStart");
 }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("CICLO_DE_VIDA","MainActivity --> onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("CICLO_DE_VIDA","MainActivity --> onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("CICLO_DE_VIDA","MainActivity --> onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("CICLO_DE_VIDA","MainActivity --> onDestroy");
    }

}