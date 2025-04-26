/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjetoMVC.Controller;


import ProjetoMVC.DAO.UserDAO;
import ProjetoMVC.Model.User;

/**
 *
 * @author LEONARDOESLABAOBARBO
 */
public class UserController {

    private static UserDAO userDAO = new UserDAO();

    public static boolean registerUser(String username, String password) {
        User user = new User(username, password);
        return userDAO.registerUser(user);
    }

    public static User validateLogin(String username, char[] passwordChars) {
        String password = new String(passwordChars);
        User user = new User(username, password);
        if (userDAO.validateLogin(user)) {
            return user;
        }
        return null;
    }

    public static boolean updatePassword(String email, char[] passwordChars) {
        String newPassword = new String(passwordChars);
        if (email.isEmpty() || newPassword.isEmpty()) {
            return false;
        }

        return userDAO.updatePassword(email, newPassword);
    }
}

