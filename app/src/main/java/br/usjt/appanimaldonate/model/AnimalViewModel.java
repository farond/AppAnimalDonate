package br.usjt.appanimaldonate.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepository animalRepository;
    private LiveData<List<Animal>> animaisResponseLiveData;
    private LiveData<Boolean> salvoComSucessoLiveData;
    private LiveData<Boolean> alteradoSucessoLiveData;
    private LiveData<Boolean> deletadoSucessoLiveData;


    public AnimalViewModel(@NonNull Application application) {
        super(application);
        Log.d("RESPOSTA","CRIACAO DA VIEWMODEL");
        animalRepository = new AnimalRepository();
        animaisResponseLiveData = animalRepository.getAllAnimais();
        salvoComSucessoLiveData = animalRepository.getSalvoSucesso();
        alteradoSucessoLiveData = animalRepository.getAlteradoSucesso();
        deletadoSucessoLiveData = animalRepository.getDeletadoSucesso();
    }

    public void getAnimais() {
        animalRepository.getAnimais();
    }

    public LiveData<List<Animal>> getAnimaisResponseLiveData() {
        return animaisResponseLiveData;
    }

    public LiveData<Boolean> getSalvoSucesso() {
        return salvoComSucessoLiveData;
    }

    public LiveData<Boolean> getAlteradoSucesso() {
        return alteradoSucessoLiveData;
    }

    public LiveData<Boolean> getDeletadoSucesso() {
        return deletadoSucessoLiveData;
    }

    public void salvarAnimal(Animal animal){
        animalRepository.salvarAnimal(animal);
    }

    public void alterarAnimal(Animal animal){
        Log.d("ANIMALKP","na view");
        animalRepository.alterarAnimal(animal);
    }

    public void deletarAnimal(int adapterPosition, Animal animal){
        animalRepository.deletarAnimal(animal);
    }
}
