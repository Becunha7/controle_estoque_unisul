
package modelo;

import dao.MovimentacaoDAO;


public class Movimentacao {
    private int id;
    private int id_produto;
    private String nomeProduto;

   
    private String dataMovimentacao;
    private int qntdMovimentada;
    private String tipoMovimentacao;
    
    
    private MovimentacaoDAO dao;
        
   public Movimentacao(int id, String nomeProduto, String dataMovimentacao, int qntdMovimentada, String tipoMovimentacao) {
    this.id = id;
    this.nomeProduto = nomeProduto;
    this.dataMovimentacao = dataMovimentacao;
    this.qntdMovimentada = qntdMovimentada;
    this.tipoMovimentacao = tipoMovimentacao;
}
 
    public Movimentacao(int id_produto, String dataMovimentacao, int qntdMovimentada, String tipoMovimentacao) {
        this.id_produto = id_produto;
        this.dataMovimentacao = dataMovimentacao;
        this.qntdMovimentada = qntdMovimentada;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Movimentacao() {
    }

    

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public void setQntdMovimentada(int qntdMovimentada) {
        this.qntdMovimentada = qntdMovimentada;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public int getQntdMovimentada() {
        return qntdMovimentada;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }
    
     public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getId_produto() {
        return id_produto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
        
    @Override
    public String toString() {
        return "ID do Produto: " + id_produto 
              + "Data da Movimentação: " + dataMovimentacao 
              + "Quantidade Movimentada: " + qntdMovimentada 
              + "Tipo Movimentação: " + tipoMovimentacao;
    }
    
   
    
}
