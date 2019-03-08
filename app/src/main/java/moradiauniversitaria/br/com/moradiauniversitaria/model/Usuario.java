package moradiauniversitaria.br.com.moradiauniversitaria.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario implements Cloneable, Parcelable {


    private String nome;
    private String email;
    private String senha;
    private String instituicao;
    private String foto;
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String instituicao, String foto, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.instituicao = instituicao;
        this.foto = foto;
        this.endereco = endereco;
    }

    protected Usuario(Parcel in) {
        nome = in.readString();
        email = in.readString();
        senha = in.readString();
        instituicao = in.readString();
        foto = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
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
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", foto='" + foto + '\'' +
                ", endereco=" + endereco +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeString(instituicao);
        dest.writeString(foto);
    }
}
