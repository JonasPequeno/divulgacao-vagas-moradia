package moradiaUniversitaria.model;

import java.util.Objects;

class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private Long numero;

    public Endereco(String rua, String cidade, String estado, Long numero) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    };

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", numero=" + numero +
                '}';
    }
}
