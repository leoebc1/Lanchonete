/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ProjetoMVC;

import ProjetoMVC.Database.Conexao;
import ProjetoMVC.View.TelaLogin;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Conexao.conectar();
        new TelaLogin().setVisible(true);
    }
}
