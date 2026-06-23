import View.LoginForm;
import controllers.ConnectDB;

import javax.swing.*;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        if (!ConnectDB.isConnect())
        {
            JOptionPane.showMessageDialog(null, "Ошибка подключения к бд! Завершение работы", "ОШИБКА", 0);
            System.exit(0);
        }
        System.out.println("Подключен!");
        LoginForm login = new LoginForm();
        login.setVisible(true);
    }
}