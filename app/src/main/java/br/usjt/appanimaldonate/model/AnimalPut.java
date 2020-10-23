package br.usjt.appanimaldonate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimalPut {

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

    public AnimalPut(String nomeAnimal,
                     String generoAnimal,
                     String racaAnimal,
                     String especieAnimal,
                     String porteAnimal,
                     String idadeAnimal,
                     String informacao,
                     boolean vacina,
                     boolean castrado) {
        this.nomeAnimal = nomeAnimal;
        this.generoAnimal = generoAnimal;
        this.racaAnimal = racaAnimal;
        this.especieAnimal = especieAnimal;
        this.porteAnimal = porteAnimal;
        this.idadeAnimal = idadeAnimal;
        this.informacao = informacao;
        this.vacina = vacina;
        this.castrado = castrado;
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



}
