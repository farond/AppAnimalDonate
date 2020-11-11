package br.usjt.appanimaldonate.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalRepository {

    private static final String ANIMAIS_SERVICE_BASE_URL = "https://crudcrud.com";


    private AnimalService animalService;
    private MutableLiveData<List<Animal>> animaisResponseMutableLiveData;
    private MutableLiveData<Boolean> salvoSucessoMutableLiveData;
    private MutableLiveData<Boolean> alteradoSucessoMutableLiveData;

    public AnimalRepository() {
        animaisResponseMutableLiveData = new MutableLiveData<>();
        salvoSucessoMutableLiveData = new MutableLiveData<>();
        alteradoSucessoMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

       animalService = new retrofit2.Retrofit.Builder()
                .baseUrl(ANIMAIS_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimalService.class);
    }

    public void getAnimais() {
        animalService.getAllAnimais()
                .enqueue(new Callback<List<Animal>>() {
                    @Override
                    public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            animaisResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Animal>> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        animaisResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<List<Animal>> getAllAnimais() {
        return animaisResponseMutableLiveData;
    }

    public LiveData<Boolean> getSalvoSucesso() {
        return salvoSucessoMutableLiveData;
    }

    public LiveData<Boolean> getAlteradoSucesso() {
        return alteradoSucessoMutableLiveData;
    }

    public void salvarAnimal(Animal animal){

        animalService.salvarAnimal(animal)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            salvoSucessoMutableLiveData.postValue(new Boolean(true));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        salvoSucessoMutableLiveData.postValue(new Boolean(false));
                    }
                });

    }

    public void alterarAnimal(Animal animal){
        Log.d("ANIMALKP","na repo");

        AnimalPut animalPut = new AnimalPut(
                animal.getNomeAnimal(),
                animal.getIdadeAnimal(),
                animal.getInformacao(),
                animal.getRacaAnimal(),
                animal.getPorteAnimal(),
                animal.getEspecieAnimal(),
                animal.getGeneroAnimal(),
                animal.isVacina(),
                animal.isCastrado(),
                animal.getImagem()
               );


        animalService.alterarAnimal(animal.getId(), animalPut)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            alteradoSucessoMutableLiveData.postValue(new Boolean(true));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        alteradoSucessoMutableLiveData.postValue(new Boolean(false));
                    }
                });
    }

    public Call<ResponseBody> deletarAnimal(Animal animal){
        return animalService.deletarAnimal(animal.getId());
    }
}
