package model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private String sexo;
    private String contato;
    private Boolean aceitaTermos;
    private String fotoPerfil;

    public Usuario() {
    }

    public Usuario(String nome, String email, String username) {
        this.nome = nome;
        this.email = email;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

        public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Boolean isAceitaTermos() {
        return aceitaTermos;
    }

    public void setAceitaTermos(Boolean aceitaTermos) {
        this.aceitaTermos = aceitaTermos;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}