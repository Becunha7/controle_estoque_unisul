package dao;

import db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;

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

    public Categoria buscarPorId(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                String tamBanco = rs.getString("tamanho").toUpperCase();
                categoria.setTamanho(enums.Tamanhos.valueOf(tamBanco));
                String embBanco = rs.getString("embalagem").toUpperCase();
                categoria.setEmbalagem(enums.Embalagens.valueOf(embBanco));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar categoria: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return categoria;
    }

    public List<Object[]> listarQuantidadeProdutosPorCategoria() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object[]> relatorio = new ArrayList<>();
        String sql = "SELECT c.nome AS categoria, COUNT(DISTINCT p.id) AS total_produtos "
                   + "FROM categoria c "
                   + "LEFT JOIN produto p ON p.id_categoria = c.id "
                   + "GROUP BY c.id "
                   + "ORDER BY c.nome ASC";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorio.add(new Object[] {
                    rs.getString("categoria"),
                    rs.getInt("total_produtos")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de categorias: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return relatorio;
    }

    public void atualizar(Categoria categoria) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE categoria SET nome = ?, tamanho = ?, embalagem = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTamanho().name().toUpperCase());
            stmt.setString(3, categoria.getEmbalagem().name().toUpperCase());
            stmt.setInt(4, categoria.getIdCategoria());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria atualizada!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void excluir(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM categoria WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria excluída!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir (verifique se há produtos nela): " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
