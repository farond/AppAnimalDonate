package br.usjt.appanimaldonate.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class AnimalResponse {

    @Expose
    private List<Animal> animais;

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}
