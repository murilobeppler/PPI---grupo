package main.java.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receita {
    private Integer id;
    private String codigo;
    private String nome;
    private String tipoArtesanato;
    private String criador;
    private String descricao;
    private String materiais;
    private LocalDate dataCriacao;
    private BigDecimal preco;
    private String caminhoPdf;

    private List<String> caminhosImagens = new ArrayList<>();

    public Receita() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoArtesanato() {
        return tipoArtesanato;
    }

    public void setTipoArtesanato(String tipoArtesanato) {
        this.tipoArtesanato = tipoArtesanato;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCaminhoPdf() {
        return caminhoPdf;
    }

    public void setCaminhoPdf(String caminhoPdf) {
        this.caminhoPdf = caminhoPdf;
    }

    public List<String> getCaminhosImagens() {
        return caminhosImagens;
    }

    public void setCaminhosImagens(List<String> caminhosImagens) {
        this.caminhosImagens = caminhosImagens;
    }
}