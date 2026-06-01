
package dao;


import db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Produto;

public class ProdutoDAO {

    public void cadastrar(Produto produto) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO produto (id_categoria, nome, quantidade, unidade, preco, qntd_min, qntd_max) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId_categoria());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getUnidade());
            stmt.setDouble(5, produto.getPreco());
            stmt.setInt(6, produto.getQntdMin());
            stmt.setInt(7, produto.getQntdMax());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Produto> listar() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.id, p.id_categoria, c.nome AS nome_categoria, p.nome, "
                   + "p.quantidade, p.unidade, p.preco, p.qntd_min, p.qntd_max "
                   + "FROM produto p "
                   + "JOIN categoria c ON p.id_categoria = c.id "
                   + "ORDER BY p.nome ASC";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_categoria"),
                    rs.getString("nome_categoria"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getString("unidade"),
                    rs.getDouble("preco"),
                    rs.getInt("qntd_min"),
                    rs.getInt("qntd_max")
                );
                produtos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void atualizar(Produto produto) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE produto SET id_categoria = ?, nome = ?, quantidade = ?, unidade = ?, "
                   + "preco = ?, qntd_min = ?, qntd_max = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId_categoria());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getUnidade());
            stmt.setDouble(5, produto.getPreco());
            stmt.setInt(6, produto.getQntdMin());
            stmt.setInt(7, produto.getQntdMax());
            stmt.setInt(8, produto.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void excluir(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Produto> listarAbaixoDoMinimo() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.id, p.id_categoria, c.nome AS nome_categoria, p.nome, "
                   + "p.quantidade, p.unidade, p.preco, p.qntd_min, p.qntd_max "
                   + "FROM produto p "
                   + "JOIN categoria c ON p.id_categoria = c.id "
                   + "WHERE p.quantidade < p.qntd_min "
                   + "ORDER BY p.nome ASC";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_categoria"),
                    rs.getString("nome_categoria"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getString("unidade"),
                    rs.getDouble("preco"),
                    rs.getInt("qntd_min"),
                    rs.getInt("qntd_max")
                );
                produtos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos abaixo do mínimo: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
}

