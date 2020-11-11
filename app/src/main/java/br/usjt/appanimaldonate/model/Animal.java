package br.usjt.appanimaldonate.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Animal implements Serializable {

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    private String id;

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

    /*@SerializedName("usuarioTelefone")
    @Expose
    private String usuarioTelefone;*/

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

    public boolean isVacina() {
        return vacina;
    }

    public void setVacina(boolean vacina) {
        this.vacina = vacina;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

   /* public String getUsuarioTelefone() {
        return usuarioTelefone;
    }

    public void setUsuarioTelefone(String usuarioTelefone) {
        this.usuarioTelefone = usuarioTelefone;
    }*/

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
