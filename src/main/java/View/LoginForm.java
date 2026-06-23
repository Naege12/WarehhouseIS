package View;

import controllers.Controller;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class LoginForm extends javax.swing.JFrame {
    private CaptchaManager captchaManager;
    private int failedCaptchaAttempts = 0;

    public LoginForm() {
        initComponents();
        // Инициализация менеджера капчи после создания компонентов
        captchaManager = new CaptchaManager(gridPanel, statusLabel, enterButton);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        backgroundPanel = new javax.swing.JPanel();
        componentPanel = new javax.swing.JPanel();
        titleLable = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        enterButton = new javax.swing.JButton();
        versionLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        captchaContainerPanel = new javax.swing.JPanel();
        gridPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WarehouseIS");
        setBackground(new java.awt.Color(44, 62, 80));
        setName("LoginForm");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        backgroundPanel.setBackground(new java.awt.Color(33, 37, 41));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        backgroundPanel.setLayout(new java.awt.GridBagLayout());

        componentPanel.setBackground(new java.awt.Color(51, 51, 51));
        componentPanel.setForeground(new java.awt.Color(51, 51, 51));
        componentPanel.setMinimumSize(new java.awt.Dimension(502, 570));
        componentPanel.setPreferredSize(new java.awt.Dimension(380, 700));
        componentPanel.setLayout(new java.awt.GridBagLayout());

        titleLable.setBackground(new java.awt.Color(44, 62, 80));
        titleLable.setFont(new java.awt.Font("Arial", 1, 24));
        titleLable.setForeground(new java.awt.Color(255, 255, 255));
        titleLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLable.setText("WarehouseIS");
        titleLable.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 5, 0);
        componentPanel.add(titleLable, gridBagConstraints);

        descriptionLabel.setFont(new java.awt.Font("Arial", 0, 14));
        descriptionLabel.setForeground(new java.awt.Color(204, 204, 204));
        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionLabel.setText("Управление складом");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        componentPanel.add(descriptionLabel, gridBagConstraints);

        loginLabel.setBackground(new java.awt.Color(52, 73, 94));
        loginLabel.setFont(new java.awt.Font("Arial", 0, 18));
        loginLabel.setForeground(new java.awt.Color(204, 204, 204));
        loginLabel.setText("Логин");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        componentPanel.add(loginLabel, gridBagConstraints);

        loginField.setColumns(20);
        loginField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199)),
                javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        componentPanel.add(loginField, gridBagConstraints);

        passwordLabel.setBackground(new java.awt.Color(52, 73, 94));
        passwordLabel.setFont(new java.awt.Font("Arial", 0, 18));
        passwordLabel.setForeground(new java.awt.Color(204, 204, 204));
        passwordLabel.setText("Пароль");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        componentPanel.add(passwordLabel, gridBagConstraints);

        passwordField.setColumns(20);
        passwordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199)),
                javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        componentPanel.add(passwordField, gridBagConstraints);

        // Контейнер для капчи
        captchaContainerPanel.setBackground(new java.awt.Color(51, 51, 51));
        captchaContainerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                null,
                "СОБЕРИТЕ ПАЗЛ",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Arial", 1, 12),
                new java.awt.Color(255, 255, 255)
        ));
        captchaContainerPanel.setMinimumSize(new java.awt.Dimension(400, 300));
        captchaContainerPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        captchaContainerPanel.setLayout(new java.awt.BorderLayout());

        // Панель для пазла
        gridPanel.setBackground(new java.awt.Color(51, 51, 51));
        gridPanel.setMinimumSize(new java.awt.Dimension(350, 230));
        gridPanel.setPreferredSize(new java.awt.Dimension(350, 280));
        gridPanel.setLayout(null); // Важно для позиционирования фрагментов

        captchaContainerPanel.add(gridPanel, java.awt.BorderLayout.CENTER);

        // Статусная строка
        statusLabel.setFont(new java.awt.Font("Arial", 1, 12));
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Перетащите фрагменты");
        captchaContainerPanel.add(statusLabel, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        componentPanel.add(captchaContainerPanel, gridBagConstraints);

        // Кнопка входа
        enterButton.setBackground(new java.awt.Color(52, 152, 219));
        enterButton.setFont(new java.awt.Font("Arial", 1, 18));
        enterButton.setForeground(new java.awt.Color(255, 255, 255));
        enterButton.setText("Войти");
        enterButton.setBorderPainted(false);
        enterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enterButton.setFocusPainted(false);
        enterButton.setPreferredSize(new java.awt.Dimension(320, 45));
        enterButton.setEnabled(false); // Изначально кнопка неактивна
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        componentPanel.add(enterButton, gridBagConstraints);

        versionLabel.setBackground(new java.awt.Color(189, 195, 199));
        versionLabel.setFont(new java.awt.Font("Arial", 0, 14));
        versionLabel.setForeground(new java.awt.Color(189, 195, 199));
        versionLabel.setText("Версия 1.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        componentPanel.add(versionLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        backgroundPanel.add(componentPanel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Controller con = new Controller();
        UserDAO userDAO = new UserDAO();
        String login = loginField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // 1. Проверка капчи
        if (!captchaManager.isCaptchaPassed()) {
            JOptionPane.showMessageDialog(this,
                    "Сначала соберите пазл правильно!",
                    "Капча",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Проверка заполнения полей
        if (login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Заполните все поля.",
                    "Предупреждение",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Поиск пользователя в БД
        User user = userDAO.getUserInDb(login);
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Пользователь с таким логином не найден",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            captchaManager.resetCaptcha();
            handleFailedCaptchaAttempt(login);
            return;
        }

        // 4. Проверка блокировки
        if (user.getIsBlocked()) {
            JOptionPane.showMessageDialog(this,
                    "Ваш пользователь был заблокирован, обратитесь к администратору",
                    "Внимание",
                    JOptionPane.WARNING_MESSAGE);
            clearFields();
            return;
        }

        // 5. Проверка пароля
        if (!user.getPassword().equals(password)) {
            user.incrementFailedAttempts();
            userDAO.updateUser(user);

            JOptionPane.showMessageDialog(this,
                    "Вы ввели неправильный логин или пароль",
                    "Внимание",
                    JOptionPane.WARNING_MESSAGE);
            captchaManager.resetCaptcha();

            // Проверяем, не достиг ли пользователь лимита попыток
            if (user.getFailedAttempts() >= 3) {
                user.setIsBlocked(true);
                userDAO.updateUser(user);
                JOptionPane.showMessageDialog(this,
                        "Вы заблокированы из-за превышения попыток входа. Обратитесь к администратору.",
                        "Блокировка",
                        JOptionPane.WARNING_MESSAGE);
                clearFields();
            }
            return;
        }

        // 6. Успешный вход
        LocalDateTime ldt = LocalDateTime.now();
        user.setLastLogin(ldt);
        user.resetFailedAttempts();
        userDAO.updateUser(user);

        if (user.isMustChangePassword()) {
            JOptionPane.showMessageDialog(this,
                    "Вам был выдан временный пароль, пожалуйста поменяйте его",
                    "Внимание",
                    JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(this,
                "Добро пожаловать " + user.getFullName(),
                "Успех",
                JOptionPane.INFORMATION_MESSAGE);

        // Переход к главной форме
        try {
            MainForm mainForm = new MainForm(this, user);
            mainForm.setVisible(true);
            this.setVisible(false);
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Ошибка при открытии главной формы: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        loginField.setText("");
        passwordField.setText("");
        if (captchaManager != null) {
            captchaManager.resetCaptcha();
        }
        failedCaptchaAttempts = 0;
    }

    private void handleFailedCaptchaAttempt(String login) {
        failedCaptchaAttempts++;
        if (failedCaptchaAttempts >= 3 && !login.isEmpty()) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserInDb(login);
            if (user != null) {
                user.setIsBlocked(true);
                userDAO.updateUser(user);
                JOptionPane.showMessageDialog(this,
                        "Вы заблокированы. Обратитесь к администратору.",
                        "Блокировка",
                        JOptionPane.WARNING_MESSAGE);
                clearFields();
            }
            failedCaptchaAttempts = 0;
        }
    }


    // Variables declaration
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel captchaContainerPanel;
    private javax.swing.JPanel componentPanel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton enterButton;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel titleLable;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration
}