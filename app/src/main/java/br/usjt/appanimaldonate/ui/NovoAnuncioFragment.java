package br.usjt.appanimaldonate.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.usjt.appanimaldonate.BuildConfig;
import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalViewModel;
import br.usjt.appanimaldonate.util.ImageUtil;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class NovoAnuncioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String NOVOANUNCIO_FRAGMENT_TAG = "novo anuncio_fragment";

    private EditText editTextNomeAnimal;
    private EditText editTextTelefoneUser;
    private Spinner spinnerEspecieAnimal;
    private Spinner spinnerPorteAnimal;
    private Spinner spinnerRacaAnimal;
    private Spinner spinnerGeneroAnimal;
    private Spinner spinnerIdadeAnimal;
    private EditText editTextInformacaoVacAnimal;
    private Switch switchVacinacaoAnimal;
    private Switch switchCastracaoAnimal;
    private Animal novoAnuncioCorrente;
    private ImageView imageViewFoto;
    private TextView textViewLinkFoto;
    private Button buttonSalvarAnuncio;
    private AnimalViewModel animalViewModel;

    private String mParam1;
    private Animal animal;

    public NovoAnuncioFragment() {

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
                    updateView(animal);
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
//        editTextIdadeAnimal = getView().findViewById(R.id.idadeAnimalEditText);
        spinnerIdadeAnimal = getView().findViewById(R.id.idadeAnimalSpinner);
        spinnerIdadeAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMIDADESELECIONADO", "POSIÇÃO-->"+position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editTextInformacaoVacAnimal = getView().findViewById(R.id.informacaoVacAnimalEditText);
        spinnerRacaAnimal = getView().findViewById(R.id.racaAnimalSpinner);
        spinnerRacaAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMRACASELECIONADO", "POSIÇÃO-->"+position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPorteAnimal = getView().findViewById(R.id.porteAnimalSpinner);
        spinnerPorteAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ITEMPORTESELECIONADO", "POSIÇÃO-->"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                if(equals("Selecione Genero")){
                    

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        switchVacinacaoAnimal = getView().findViewById(R.id.vacinacaoAnimalSwitch);
        switchVacinacaoAnimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editTextInformacaoVacAnimal.setVisibility(View.VISIBLE);
                }
                else{
                    editTextInformacaoVacAnimal.setVisibility(View.INVISIBLE);
                    editTextInformacaoVacAnimal.setText("");
                }
            }
        });

        switchCastracaoAnimal = getView().findViewById(R.id.castracaoAnimalSwitch);
        imageViewFoto = getView().findViewById(R.id.imageViewFoto);
        textViewLinkFoto = getView().findViewById(R.id.textViewLinkFoto);
        editTextTelefoneUser = getView().findViewById(R.id.telefoneUserEditText);
        buttonSalvarAnuncio = getView().findViewById(R.id.salvarAnuncioButton);

        textViewLinkFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        buttonSalvarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        if(animal !=null){
            novoAnuncioCorrente = animal;
            editTextNomeAnimal.setText(novoAnuncioCorrente.getNomeAnimal());
//            editTextIdadeAnimal.setText(novoAnuncioCorrente.getIdadeAnimal());
            editTextInformacaoVacAnimal.setText(novoAnuncioCorrente.getInformacao());
            spinnerIdadeAnimal.setSelection(((ArrayAdapter)spinnerIdadeAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getIdadeAnimal()));;
            spinnerRacaAnimal.setSelection(((ArrayAdapter)spinnerRacaAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getRacaAnimal()));
            spinnerPorteAnimal.setSelection(((ArrayAdapter)spinnerPorteAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getPorteAnimal()));
            spinnerEspecieAnimal.setSelection(((ArrayAdapter)spinnerEspecieAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getEspecieAnimal()));
            spinnerGeneroAnimal.setSelection(((ArrayAdapter)spinnerGeneroAnimal.getAdapter()).getPosition(novoAnuncioCorrente.getGeneroAnimal()));
//          spinnerRacaAnimal.setSelection(((ArrayAdapter)spinnerRacaAnimal.getAdapter()).getPosition(animalCorrente.getRacaAnimal()));
            switchVacinacaoAnimal.setChecked(novoAnuncioCorrente.isVacina());
            switchCastracaoAnimal.setChecked(novoAnuncioCorrente.isCastrado());
            editTextTelefoneUser.setText(novoAnuncioCorrente.getUsuarioTelefone());
            if(novoAnuncioCorrente.getImagem() == null || novoAnuncioCorrente.getImagem().isEmpty()){
                imageViewFoto.setImageResource(R.drawable.ic_photo);
            }else{
                imageViewFoto.setImageBitmap(ImageUtil.decode(novoAnuncioCorrente.getImagem()));
            }
        }

    }

    public void salvar(){
        novoAnuncioCorrente.setNomeAnimal(editTextNomeAnimal.getText().toString());
//        novoAnuncioCorrente.setIdadeAnimal(editTextIdadeAnimal.getText().toString());
        novoAnuncioCorrente.setIdadeAnimal(spinnerIdadeAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setInformacao(editTextInformacaoVacAnimal.getText().toString());
        novoAnuncioCorrente.setRacaAnimal(spinnerRacaAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setPorteAnimal(spinnerPorteAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setEspecieAnimal(spinnerEspecieAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setGeneroAnimal(spinnerGeneroAnimal.getSelectedItem().toString());
        novoAnuncioCorrente.setVacina(switchVacinacaoAnimal.isChecked());
        novoAnuncioCorrente.setCastrado(switchCastracaoAnimal.isChecked());
        novoAnuncioCorrente.setUsuarioTelefone(editTextTelefoneUser.getText().toString());
        if(animal == null){
            animalViewModel.salvarAnimal(novoAnuncioCorrente);
        }else{
            Log.d("ANIMALKP","alterar");
            animalViewModel.alterarAnimal(novoAnuncioCorrente);
        }
        limparCampos();
    }

    private void updateView(Animal animal) {
        if(animalViewModel.getAlteradoSucesso().equals(true)){
            animal.setNomeAnimal(animal.getNomeAnimal());
            animal.setGeneroAnimal(animal.getGeneroAnimal());
            animal.setRacaAnimal(animal.getRacaAnimal());
            animal.setEspecieAnimal(animal.getEspecieAnimal());
            animal.setPorteAnimal(animal.getPorteAnimal());
            animal.setIdadeAnimal(animal.getIdadeAnimal());
            animal.setInformacao(animal.getInformacao());
            animal.setUsuarioTelefone(animal.getUsuarioTelefone());
        }
    }

    private void limparCampos(){
        editTextNomeAnimal.setText("");
//        editTextIdadeAnimal.setText("");
        editTextInformacaoVacAnimal.setText("");
        spinnerIdadeAnimal.setSelection(0);
        spinnerRacaAnimal.setSelection(0);
        spinnerPorteAnimal.setSelection(0);
        spinnerEspecieAnimal.setSelection(0);
        spinnerGeneroAnimal.setSelection(0);
        switchVacinacaoAnimal.setChecked(false);
        switchCastracaoAnimal.setChecked(false);
        imageViewFoto.setImageResource(R.drawable.ic_photo);
        editTextTelefoneUser.setText("");
    }

    private void tirarFoto(){
        dispatchTakePictureIntent();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);
            novoAnuncioCorrente.setImagem(ImageUtil.encode(imageBitmap));
            Log.d("IMAGEMBITMAPENCODED-->",novoAnuncioCorrente.getImagem());
        }

      /* if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();
        }*/

    }

    // a partir daqui código para gravar a foto local no file storage

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntentFile() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(
                        getActivity().getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", photoFile);


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewFoto.getWidth();
        int targetH = imageViewFoto.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageViewFoto.setImageBitmap(bitmap);
        novoAnuncioCorrente.setImagem(ImageUtil.encode(bitmap));
        Log.d("IMAGEMBITMAPENCODED-->",novoAnuncioCorrente.getImagem());

    }


}