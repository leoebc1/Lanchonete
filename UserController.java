/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjetoMVC.Controller;

import ProjetoMVC.DAO.UsuarioDAO;
import ProjetoMVC.Model.Usuario;

/**
 *
 * @author LEONARDOESLABAOBARBO
 */
public class UsuarioController {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static boolean registrarUsuario(String username, String senha) {
        Usuario usuario = new Usuario(username, senha);
        return usuarioDAO.registrarUsuario(usuario);
    }

    public static Usuario validarLogin(String username, char[] charSenha) {
        String senha = new String(charSenha);
        Usuario usuario = new Usuario(username, senha);
        if (usuarioDAO.validarLogin(usuario)) {
            return usuario;
        }
        return null;

    }

    public static boolean atualizarSenha(String email, char[] charSenha){
        String novaSenha = new String(charSenha);
        if (email.isEmpty() || novaSenha.isEmpty()){
            return false;
        }
        
        return usuarioDAO.atualizarSenha(email, novaSenha);
    }
}
