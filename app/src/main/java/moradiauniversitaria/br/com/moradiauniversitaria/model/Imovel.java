package moradiauniversitaria.br.com.moradiauniversitaria.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Imovel implements Parcelable {
    private String sobreVaga;
    private String sobreImovel;
    private int valorVaga;
    private String foto;
    private Endereco endereco;
    private String emailUser;
    private String contato;
    private String _id;


    public Imovel() {
    }

    public Imovel(String sobreVaga,String contato, String emailUser, String sobreImovel, int valorVaga, String foto, Endereco endereco) {
        this.sobreVaga = sobreVaga;
        this.emailUser = emailUser;
        this.sobreImovel = sobreImovel;
        this.valorVaga = valorVaga;
        this.foto = foto;
        this.endereco = endereco;


    }

    protected Imovel(Parcel in) {
        sobreVaga = in.readString();
        sobreImovel = in.readString();
        foto = in.readString();
    }

    public static final Creator<Imovel> CREATOR = new Creator<Imovel>() {
        @Override
        public Imovel createFromParcel(Parcel in) {
            return new Imovel(in);
        }

        @Override
        public Imovel[] newArray(int size) {
            return new Imovel[size];
        }
    };

    public String getSobreVaga() {
        return sobreVaga;
    }

    public void setSobreVaga(String sobreVaga) {
        this.sobreVaga = sobreVaga;
    }


    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getSobreImovel() {
        return sobreImovel;
    }

    public void setSobreImovel(String sobreImovel) {
        this.sobreImovel = sobreImovel;
    }

    public int getValorVaga() {
        return valorVaga;
    }

    public void setValorVaga(int valorVaga) {
        this.valorVaga = valorVaga;
    }

    public String getFoto() {
        return foto;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String get_id() {
        return _id;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "sobreVaga='" + sobreVaga + '\'' +
                ", sobreImovel='" + sobreImovel + '\'' +
                ", valorVaga=" + valorVaga +
                ", foto='" + foto + '\'' +
                ", endereco=" + endereco +
                ", emailUser='" + emailUser + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sobreVaga);
        dest.writeString(sobreImovel);
        dest.writeString(foto);
        dest.writeString(getEndereco().getEstado());
    }
}
