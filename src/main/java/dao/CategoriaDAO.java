package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
     public List<Categoria> listar() {
        Connection con = Conexao.getConnection(); 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY nome ASC";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                
              
                c.setIdCategoria(rs.getInt("id")); 
                c.setNome(rs.getString("nome"));
                
                
                String tamBanco = rs.getString("tamanho").toUpperCase();
                c.setTamanho(enums.Tamanhos.valueOf(tamBanco)); 
                
                String embBanco = rs.getString("embalagem").toUpperCase();
                c.setEmbalagem(enums.Embalagens.valueOf(embBanco)); 
                
                categorias.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs); 
        }
        return categorias;
    }
}
