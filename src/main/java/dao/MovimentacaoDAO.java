package dao;

import db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Movimentacao;

public class MovimentacaoDAO {

    public void registrar(Movimentacao mov) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmtMov = null;
        PreparedStatement stmtProd = null;
        PreparedStatement stmtConsulta = null;
        ResultSet rsConsulta = null;

        String sqlMov = "INSERT INTO movimentacao (id_produto, data_movimentacao, qntd_movimentada, tipo_movimentacao) "
                      + "VALUES (?, ?, ?, ?)";

        String sqlProd;
        if ("ENTRADA".equalsIgnoreCase(mov.getTipoMovimentacao())) {
            sqlProd = "UPDATE produto SET quantidade = quantidade + ? WHERE id = ?";
        } else {
            sqlProd = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
        }

        try {
            con.setAutoCommit(false);

            if ("SAIDA".equalsIgnoreCase(mov.getTipoMovimentacao())) {
                String sqlVerifica = "SELECT quantidade FROM produto WHERE id = ?";
                stmtConsulta = con.prepareStatement(sqlVerifica);
                stmtConsulta.setInt(1, mov.getId_produto());
                rsConsulta = stmtConsulta.executeQuery();
                if (rsConsulta.next() && rsConsulta.getInt("quantidade") < mov.getQntdMovimentada()) {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente em estoque para essa saída.");
                    con.rollback();
                    return;
                }
            }

            stmtMov = con.prepareStatement(sqlMov);
            stmtMov.setInt(1, mov.getId_produto());
            stmtMov.setString(2, mov.getDataMovimentacao());
            stmtMov.setInt(3, mov.getQntdMovimentada());
            stmtMov.setString(4, mov.getTipoMovimentacao().toUpperCase());
            stmtMov.executeUpdate();

            stmtProd = con.prepareStatement(sqlProd);
            stmtProd.setInt(1, mov.getQntdMovimentada());
            stmtProd.setInt(2, mov.getId_produto());
            stmtProd.executeUpdate();

            con.commit();
            JOptionPane.showMessageDialog(null, "Movimentação registrada com sucesso!");
        } catch (SQLException ex) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Erro ao fazer rollback: " + rollbackEx.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação: " + ex.getMessage());
        } finally {
            try {
                if (rsConsulta != null) rsConsulta.close();
                if (stmtConsulta != null) stmtConsulta.close();
                if (stmtProd != null) stmtProd.close();
                if (stmtMov != null) stmtMov.close();
                if (con != null) { con.setAutoCommit(true); con.close(); }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    public List<Movimentacao> listar() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimentacao> lista = new ArrayList<>();
        String sql = "SELECT m.id, p.nome AS nome_produto, m.data_movimentacao, "
                   + "m.qntd_movimentada, m.tipo_movimentacao "
                   + "FROM movimentacao m "
                   + "JOIN produto p ON m.id_produto = p.id "
                   + "ORDER BY m.data_movimentacao DESC";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Movimentacao m = new Movimentacao(
                    rs.getInt("id"),
                    rs.getString("nome_produto"),
                    rs.getString("data_movimentacao"),
                    rs.getInt("qntd_movimentada"),
                    rs.getString("tipo_movimentacao")
                );
                lista.add(m);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar movimentações: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public List<Movimentacao> listarPorProduto(int idProduto) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimentacao> lista = new ArrayList<>();
        String sql = "SELECT m.id, p.nome AS nome_produto, m.data_movimentacao, "
                   + "m.qntd_movimentada, m.tipo_movimentacao "
                   + "FROM movimentacao m "
                   + "JOIN produto p ON m.id_produto = p.id "
                   + "WHERE m.id_produto = ? "
                   + "ORDER BY m.data_movimentacao DESC";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Movimentacao m = new Movimentacao(
                    rs.getInt("id"),
                    rs.getString("nome_produto"),
                    rs.getString("data_movimentacao"),
                    rs.getInt("qntd_movimentada"),
                    rs.getString("tipo_movimentacao")
                );
                lista.add(m);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar movimentações: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public String[] produtoMaisEntradaSaida() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] resultado = new String[2];

        String sqlEntrada = "SELECT p.nome, SUM(m.qntd_movimentada) AS total_entrada "
                          + "FROM movimentacao m "
                          + "JOIN produto p ON m.id_produto = p.id "
                          + "WHERE m.tipo_movimentacao = 'ENTRADA' "
                          + "GROUP BY m.id_produto, p.nome "
                          + "ORDER BY total_entrada DESC LIMIT 1";
        String sqlSaida = "SELECT p.nome, SUM(m.qntd_movimentada) AS total_saida "
                         + "FROM movimentacao m "
                         + "JOIN produto p ON m.id_produto = p.id "
                         + "WHERE m.tipo_movimentacao = 'SAIDA' "
                         + "GROUP BY m.id_produto, p.nome "
                         + "ORDER BY total_saida DESC LIMIT 1";
        try {
            stmt = con.prepareStatement(sqlEntrada);
            rs = stmt.executeQuery();
            if (rs.next()) {
                resultado[0] = rs.getString("nome");
            }
            rs.close();
            stmt.close();

            stmt = con.prepareStatement(sqlSaida);
            rs = stmt.executeQuery();
            if (rs.next()) {
                resultado[1] = rs.getString("nome");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto mais movimentado: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return resultado;
    }
}
