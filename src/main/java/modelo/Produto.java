package modelo;

public class Produto {

    private int id;
    private int id_categoria;
    private String nome_categoria;
    private String nome;
    private int quantidade;
    private String unidade;
    private double preco;
    private int qntdMin;
    private int qntdMax;
   
    public Produto() {
    }

    public Produto(int id, int id_categoria, String nome, int quantidade, double preco, int qntdMin, int qntdMax) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.qntdMin = qntdMin;
        this.qntdMax = qntdMax;
    }
    
    public Produto(int id, int id_categoria, String nome_categoria, String nome, int quantidade, String unidade, double preco, int qntdMin, int qntdMax) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nome_categoria = nome_categoria;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.preco = preco;
        this.qntdMin = qntdMin;
        this.qntdMax = qntdMax;
    }

    // GETTERS E SETTERS
    
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setQntdMin(int qntdMin) {
        this.qntdMin = qntdMin;
    }

    public void setQntdMax(int qntdMax) {
        this.qntdMax = qntdMax;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public int getQntdMin() {
        return qntdMin;
    }

    public int getQntdMax() {
        return qntdMax;
    }
      
    public String getNome_categoria() {
        return nome_categoria;
    }

    public String getUnidade() {
        return unidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
     public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}







