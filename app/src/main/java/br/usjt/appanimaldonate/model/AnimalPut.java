package br.usjt.appanimaldonate.model;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimalPut {

    @SerializedName("nome")
    @Expose
    private String nomeAnimal;

    @SerializedName("idade")
    @Expose
    private String idadeAnimal;

    @SerializedName("informacao")
    @Expose
    private String informacao;

    @SerializedName("raca")
    @Expose
    private String racaAnimal;

    @SerializedName("porte")
    @Expose
    private String porteAnimal;

    @SerializedName("especie")
    @Expose
    private String especieAnimal;

    @SerializedName("genero")
    @Expose
    private String generoAnimal;

    @SerializedName("vacina")
    @Expose
    private boolean vacina;

    @SerializedName("castrado")
    @Expose
    private boolean castrado;

    @SerializedName("imagem")
    @Expose
    private String imagem;

    @SerializedName("usuarioTelefone")
    @Expose
    private String usuarioTelefone;


    @Ignore
    public AnimalPut(
            String nomeAnimal,
            String idadeAnimal,
            String informacao,
            String racaAnimal,
            String porteAnimal,
            String especieAnimal,
            String generoAnimal,
            boolean vacina,
            boolean castrado,
            String imagem,
            String usuarioTelefone) {
        this.nomeAnimal = nomeAnimal;
        this.idadeAnimal = idadeAnimal;
        this.informacao = informacao;
        this.racaAnimal = racaAnimal;
        this.porteAnimal = porteAnimal;
        this.especieAnimal = especieAnimal;
        this.generoAnimal = generoAnimal;
        this.vacina = vacina;
        this.castrado = castrado;
        this.imagem = imagem;
        this.usuarioTelefone = usuarioTelefone;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
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

    public String getRacaAnimal() {
        return racaAnimal;
    }

    public void setRacaAnimal(String racaAnimal) {
        this.racaAnimal = racaAnimal;
    }

    public String getPorteAnimal() {
        return porteAnimal;
    }

    public void setPorteAnimal(String porteAnimal) {
        this.porteAnimal = porteAnimal;
    }

    public String getEspecieAnimal() {
        return especieAnimal;
    }

    public void setEspecieAnimal(String especieAnimal) {
        this.especieAnimal = especieAnimal;
    }

    public String getGeneroAnimal() {
        return generoAnimal;
    }

    public void setGeneroAnimal(String generoAnimal) {
        this.generoAnimal = generoAnimal;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getUsuarioTelefone() {
        return usuarioTelefone;
    }

    public void setUsuarioTelefone(String usuarioTelefone) {
        this.usuarioTelefone = usuarioTelefone;
    }
}
