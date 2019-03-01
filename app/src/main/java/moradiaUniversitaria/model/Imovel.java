package moradiaUniversitaria.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class Imovel {
    private String sobreVaga;
    private String sobreImovel;
    private Number valorVaga;
    private String foto;
    private Endereco endereco;

    public Imovel() {
    }

    public Imovel(String sobreVaga, String sobreImovel, Number valorVaga, String foto, Endereco endereco) {
        this.sobreVaga = sobreVaga;
        this.sobreImovel = sobreImovel;
        this.valorVaga = valorVaga;
        this.foto = foto;
        this.endereco = endereco;
    }

    public String getSobreVaga() {
        return sobreVaga;
    }

    public void setSobreVaga(String sobreVaga) {
        this.sobreVaga = sobreVaga;
    }

    public String getSobreImovel() {
        return sobreImovel;
    }

    public void setSobreImovel(String sobreImovel) {
        this.sobreImovel = sobreImovel;
    }

    public Number getValorVaga() {
        return valorVaga;
    }

    public void setValorVaga(Number valorVaga) {
        this.valorVaga = valorVaga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "sobreVaga='" + sobreVaga + '\'' +
                ", sobreImovel='" + sobreImovel + '\'' +
                ", valorVaga=" + valorVaga +
                ", foto=" + foto +
                ", endereco=" + endereco +
                '}';
    }
}
