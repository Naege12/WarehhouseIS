/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panels;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author User
 */
public class usersPanel extends javax.swing.JPanel {
    UserDAO userDAO = new UserDAO();
    LocalDate ld;

    /**
     * Creates new form usersPanel
     */
    public usersPanel() {
        initComponents();
        loadUserData();
        ld = LocalDate.now();
        usersDateLabel.setText(ld.toString());
    }

    private void loadUserData() {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        try {
            List<User> users = userDAO.getAllUsers();

            if (users.isEmpty()) {
                model.addRow(new Object[]{"—", "Ошибка загрузки пользователей", "—", "—", "—", "—"});
            } else {
                for (User u : users) {
                    model.addRow(new Object[]{
                            u.getId(),
                            u.getLogin(),
                            u.getPassword(),
                            u.getFullName(),
                            u.getRole(),
                            u.getIsBlocked(),
                            u.getLastLogin(),
                            u.isMustChangePassword(),
                            u.getCreatedAt()
                    });
                }
            }
            System.out.println("✅ Загружено пользователей: " + users.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditUserDialog(User user) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Редактирование пользователя", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtLogin = new JTextField(user.getLogin());
        JTextField txtFullName = new JTextField(user.getFullName());
        JComboBox<String> cmbRole = new JComboBox<>(new String[]{"user", "admin"});
        cmbRole.setSelectedItem(user.getRole());
        JCheckBox chkBlocked = new JCheckBox("Заблокирован", user.getIsBlocked());
        JCheckBox chkMustChange = new JCheckBox("Требуется смена пароля", user.isMustChangePassword());
        JTextField txtPassword = new JTextField(); // для смены пароля (опционально)

        formPanel.add(new JLabel("Логин:"));
        formPanel.add(txtLogin);
        formPanel.add(new JLabel("ФИО:"));
        formPanel.add(txtFullName);
        formPanel.add(new JLabel("Роль:"));
        formPanel.add(cmbRole);
        formPanel.add(new JLabel("Статус:"));
        formPanel.add(chkBlocked);
        formPanel.add(new JLabel("Смена пароля:"));
        formPanel.add(chkMustChange);
        formPanel.add(new JLabel("Новый пароль:"));
        formPanel.add(txtPassword);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Сохранить");
        JButton btnCancel = new JButton("Отмена");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dialog.dispose());

        btnSave.addActionListener(e -> {
            if (txtLogin.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Введите логин");
                return;
            }

            user.setLogin(txtLogin.getText().trim());
            user.setFullName(txtFullName.getText().trim());
            user.setRole((String) cmbRole.getSelectedItem());
            user.setIsBlocked(chkBlocked.isSelected());
            user.setMustChangePassword(chkMustChange.isSelected());

            // Если введён новый пароль — обновляем
            if (!txtPassword.getText().trim().isEmpty()) {
                user.setPassword(txtPassword.getText().trim());
            }

            boolean success = userDAO.updateUser(user);
            if (success) {
                dialog.dispose();
                loadUserData();
                JOptionPane.showMessageDialog(this, "Пользователь обновлён");
            } else {
                JOptionPane.showMessageDialog(dialog, "Ошибка обновления");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usersHeaderPanel = new javax.swing.JPanel();
        namePanelLabel = new javax.swing.JLabel();
        usersDateLabel = new javax.swing.JLabel();
        usersContentPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        usersTabelPanel = new javax.swing.JPanel();
        userScrollPane = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(900, 148));
        setMinimumSize(new java.awt.Dimension(900, 148));
        setPreferredSize(new java.awt.Dimension(900, 148));
        setLayout(new java.awt.BorderLayout());

        usersHeaderPanel.setBackground(new java.awt.Color(33, 37, 41));
        usersHeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        usersHeaderPanel.setLayout(new java.awt.GridBagLayout());

        namePanelLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        namePanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        namePanelLabel.setText("Управление пользователями");
        namePanelLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        usersHeaderPanel.add(namePanelLabel, new java.awt.GridBagConstraints());

        usersDateLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        usersDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        usersDateLabel.setText("время");
        usersDateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        usersHeaderPanel.add(usersDateLabel, new java.awt.GridBagConstraints());

        add(usersHeaderPanel, java.awt.BorderLayout.PAGE_START);

        usersContentPanel.setBackground(new java.awt.Color(33, 37, 41));
        usersContentPanel.setLayout(new java.awt.BorderLayout());

        buttonPanel.setBackground(new java.awt.Color(33, 37, 41));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonPanel.setMaximumSize(new java.awt.Dimension(900, 50));
        buttonPanel.setMinimumSize(new java.awt.Dimension(900, 35));
        buttonPanel.setPreferredSize(new java.awt.Dimension(900, 50));

        addButton.setBackground(new java.awt.Color(40, 167, 69));
        addButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Добавить");
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new java.awt.Dimension(130, 35));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(addButton);

        editButton.setBackground(new java.awt.Color(255, 193, 7));
        editButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Редактировать");
        editButton.setFocusPainted(false);
        editButton.setMaximumSize(new java.awt.Dimension(190, 29));
        editButton.setPreferredSize(new java.awt.Dimension(170, 35));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(editButton);

        deleteButton.setBackground(new java.awt.Color(220, 53, 69));
        deleteButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Удалить");
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new java.awt.Dimension(130, 35));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(deleteButton);

        refreshButton.setBackground(new java.awt.Color(255, 153, 0));
        refreshButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("Обновить");
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new java.awt.Dimension(130, 35));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(refreshButton);

        usersContentPanel.add(buttonPanel, java.awt.BorderLayout.NORTH);

        usersTabelPanel.setBackground(new java.awt.Color(33, 37, 41));
        usersTabelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ПОЛЬЗОВАТЕЛИ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        usersTabelPanel.setMinimumSize(new java.awt.Dimension(900, 900));
        usersTabelPanel.setPreferredSize(new java.awt.Dimension(900, 900));
        usersTabelPanel.setLayout(new java.awt.BorderLayout());

        userScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        userTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Логин", "Пароль", "ФИО", "Роль", "Блокировка", "Дата последнего входа", "Требуеться смена пароля", "Дата регистрации"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        userTable.setPreferredSize(new java.awt.Dimension(750, 120));
        userScrollPane.setViewportView(userTable);

        usersTabelPanel.add(userScrollPane, java.awt.BorderLayout.CENTER);

        usersContentPanel.add(usersTabelPanel, java.awt.BorderLayout.CENTER);

        add(usersContentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        UserAddForm addForm = new UserAddForm();
        addForm.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пользователя для редактирования");
            return;
        }

        Long userId = (Long) userTable.getValueAt(selectedRow, 0);
        User user = userDAO.getUserById(userId);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Пользователь не найден");
            return;
        }

        showEditUserDialog(user);
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        loadUserData();
        JOptionPane.showMessageDialog(this, "Данные успешно обнавленны", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_refreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel namePanelLabel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JScrollPane userScrollPane;
    private javax.swing.JTable userTable;
    private javax.swing.JPanel usersContentPanel;
    private javax.swing.JLabel usersDateLabel;
    private javax.swing.JPanel usersHeaderPanel;
    private javax.swing.JPanel usersTabelPanel;
    // End of variables declaration//GEN-END:variables
}
