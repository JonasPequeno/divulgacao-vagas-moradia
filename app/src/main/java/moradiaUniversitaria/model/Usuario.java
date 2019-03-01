package moradiaUniversitaria.model;

public class Usuario {

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
}
