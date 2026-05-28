package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Categoria;
import db.Conexao; 

public class CategoriaDAO {

    public void cadastrar(Categoria categoria) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO categoria (nome, tamanho, embalagem) VALUES (?, ?, ?)";
    } 
}