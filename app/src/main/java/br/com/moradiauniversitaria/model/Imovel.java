package br.com.moradiauniversitaria.model;

import android.net.Uri;

public class Imovel {

    private String sobreVaga;
    private String sobreImovel;
    private Number valorVaga;
    private Uri foto;

    public Imovel() {
    }

    public Imovel(String sobreVaga, String sobreImovel, float valorVaga, Uri foto) {
        this.sobreVaga = sobreVaga;
        this.sobreImovel = sobreImovel;
        this.valorVaga = valorVaga;
        this.foto = foto;
    };

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

    public void setValorVaga(float valorVaga) {
        this.valorVaga = valorVaga;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "sobreVaga='" + sobreVaga + '\'' +
                ", sobreImovel='" + sobreImovel + '\'' +
                ", valorVaga=" + valorVaga +
                ", foto='" + foto + '\'' +
                '}';
    }
}
