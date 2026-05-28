package modelo;

import enums.Embalagens;
import enums.Tamanhos;

public class Categoria {

    private int idCategoria;
    private String nome;
    private Tamanhos tamanho;
    private Embalagens embalagem;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nome, Tamanhos tamanho, Embalagens embalagem) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tamanhos getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanhos tamanho) {
        this.tamanho = tamanho;
    }

    public Embalagens getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagens embalagem) {
        this.embalagem = embalagem;
    }

}
