package db;

import java.sql.Connection;

public class ConexaoMySQL {
    public static Connection getConexao() {
        return Conexao.getConnection(); // delega para a Conexao principal
    }
}
