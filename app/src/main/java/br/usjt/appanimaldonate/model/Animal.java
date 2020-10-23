package br.usjt.appanimaldonate.model;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Animal implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("nome")
    @Expose
    private String nomeAnimal;

    @SerializedName("genero")
    @Expose
    private String generoAnimal;

    @SerializedName("raca")
    @Expose
    private String racaAnimal;

    @SerializedName("especie")
    @Expose
    private String especieAnimal;

    @SerializedName("porte")
    @Expose
    private String porteAnimal;

    @SerializedName("idade")
    @Expose
    private String idadeAnimal;

    @SerializedName("vacina")
    @Expose
    private boolean vacina;

    @SerializedName("informacao")
    @Expose
    private String informacao;

    @SerializedName("castrado")
    @Expose
    private boolean castrado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getGeneroAnimal() {
        return generoAnimal;
    }

    public void setGeneroAnimal(String generoAnimal) {
        this.generoAnimal = generoAnimal;
    }

    public String getRacaAnimal() {
        return racaAnimal;
    }

    public void setRacaAnimal(String racaAnimal) {
        this.racaAnimal = racaAnimal;
    }

    public String getEspecieAnimal() {
        return especieAnimal;
    }

    public void setEspecieAnimal(String especieAnimal) {
        this.especieAnimal = especieAnimal;
    }

    public String getPorteAnimal() {
        return porteAnimal;
    }

    public void setPorteAnimal(String porteAnimal) {
        this.porteAnimal = porteAnimal;
    }

    public String getIdadeAnimal() {
        return idadeAnimal;
    }

    public void setIdadeAnimal(String idadeAnimal) {
        this.idadeAnimal = idadeAnimal;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public boolean isVacina() {
        return vacina;
    }

    public void setVacina(boolean vacina) {
        this.vacina = vacina;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", nomeAnimal='" + nomeAnimal + '\'' +
                ", idadeAnimal='" + idadeAnimal + '\'' +
                ", informacao='" + informacao + '\'' +
                '}';
    }
}
