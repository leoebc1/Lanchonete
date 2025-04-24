/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjetoMVC.Database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LEONARDOESLABAOBARBO
 */
public class Conexao {

    private static final Dotenv dotenv = Dotenv.load();
    private static Connection connection;
    private static final String URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection conectar() {
        try {
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                criarTabela();
                System.out.println("Conectou aleluia");
            }
        } catch (SQLException error) {
            System.out.println("deu ruim sabosta");
            throw new RuntimeException("Erro na conexao com o banco de dados");
        }
        
        return connection;
    }

    private static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "usuario VARCHAR(255) NOT NULL UNIQUE, "
                + "senha VARCHAR(255) NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e){
                throw new RuntimeException("Erro ao criar tabela");
                }
    }

}
