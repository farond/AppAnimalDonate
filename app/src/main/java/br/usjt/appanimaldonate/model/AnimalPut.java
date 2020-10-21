package br.usjt.appanimaldonate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimalPut {

    @SerializedName("nome")
    @Expose
    private String nomeAnimal;

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
    private Boolean vacina;

    @SerializedName("informacao")
    @Expose
    private String informacao;

    @SerializedName("castrado")
    @Expose
    private Boolean castrado;

    public AnimalPut(String nomeAnimal, String racaAnimal, String porteAnimal, String idadeAnimal, String especieAnimal, String idadeAnimal1, String informacao, Boolean castrado, Boolean vacina) {
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
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

    public Boolean getVacina() {
        return vacina;
    }

    public void setVacina(Boolean vacina) {
        this.vacina = vacina;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public Boolean getCastrado() {
        return castrado;
    }

    public void setCastrado(Boolean castrado) {
        this.castrado = castrado;
    }

    public AnimalPut(String nomeAnimal, String racaAnimal, String especieAnimal, String porteAnimal, String idadeAnimal, Boolean vacina, String informacao, Boolean castrado) {
        this.nomeAnimal = nomeAnimal;
        this.racaAnimal = racaAnimal;
        this.especieAnimal = especieAnimal;
        this.porteAnimal = porteAnimal;
        this.idadeAnimal = idadeAnimal;
        this.vacina = vacina;
        this.informacao = informacao;
        this.castrado = castrado;
    }
}
