package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelo.Categoria;
import db.Conexao;

public class CategoriaDAO {

    public void cadastrar(Categoria categoria) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO categoria (nome, tamanho, embalagem) VALUES (?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTamanho().name().toUpperCase());
            stmt.setString(3, categoria.getEmbalagem().name().toUpperCase());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt); 
        }
    }
}
