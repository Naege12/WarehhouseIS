/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panels;

import dao.MovementDAO;
import dao.ProductDAO;
import dao.WarehouseDAO;
import model.Movement;
import model.Product;
import model.User;
import model.Warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author User
 */
public class IncomePanel extends javax.swing.JPanel {
    LocalDate ld;
    private final MovementDAO movementDAO = new MovementDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final WarehouseDAO warehouseDAO = new WarehouseDAO();
    private User currentUser;
    /**
     * Creates new form IncomePanel
     */
    public IncomePanel() {
        initComponents();
        ld = LocalDate.now();
        incomeDateLabel.setText(ld.toString());
        loadComboBoxes();
        setupListeners();
    }

    public IncomePanel(User user) {
        this();
        this.currentUser = user;
    }

    private void loadComboBoxes() {
        // Загрузка товаров
        nameProductComboBox.removeAllItems();
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            nameProductComboBox.addItem(p.getId() + " - " + p.getName());
        }

        // Загрузка складов
        nameWarehouseComboBox.removeAllItems();
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        for (Warehouse w : warehouses) {
            nameWarehouseComboBox.addItem(w.getId() + " - " + w.getName());
        }
    }

    private void setupListeners() {
        addButton.addActionListener(e -> processIncome());
    }


    private void processIncome() {

        if (!validateFields()) {
            return;
        }

        try {

            BigDecimal quantity = new BigDecimal(quantityTextField.getText().trim());
            BigDecimal price = new BigDecimal(priceTextField.getText().trim());
            String supplier = providerTextField.getText().trim();
            String cell = cellTextField.getText().trim();


            String productStr = (String) nameProductComboBox.getSelectedItem();
            Long productId = Long.parseLong(productStr.split(" - ")[0]);

            String warehouseStr = (String) nameWarehouseComboBox.getSelectedItem();
            Long warehouseId = Long.parseLong(warehouseStr.split(" - ")[0]);


            Movement movement = new Movement();
            movement.setType(Movement.Type.IN);
            movement.setDocNumber("IN-" + System.currentTimeMillis());
            movement.setDocDate(LocalDate.now());
            movement.setProductId(productId);
            movement.setWarehouseId(warehouseId);
            movement.setUserId(currentUser != null ? currentUser.getId() : 1L);
            movement.setQuantity(quantity);
            movement.setPrice(price);
            movement.setToCell(cell);
            movement.setCounterparty(supplier);
            movement.setComment("Приход от " + supplier);


            boolean success = movementDAO.saveMovement(movement);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "✅ Приход оформлен успешно!\n" +
                                "Товар: " + productStr + "\n" +
                                "Количество: " + quantity + "\n" +
                                "Сумма: " + quantity.multiply(price),
                        "Успех",
                        JOptionPane.INFORMATION_MESSAGE
                );
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Ошибка при оформлении прихода",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Проверьте правильность числовых полей (количество, цена)",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Ошибка: " + ex.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private boolean validateFields() {

        if (nameProductComboBox.getSelectedIndex() == -1) {
            showError("Выберите товар");
            return false;
        }


        if (nameWarehouseComboBox.getSelectedIndex() == -1) {
            showError("Выберите склад");
            return false;
        }


        String quantityText = quantityTextField.getText().trim();
        if (quantityText.isEmpty()) {
            showError("Введите количество");
            return false;
        }
        try {
            BigDecimal qty = new BigDecimal(quantityText);
            if (qty.compareTo(BigDecimal.ZERO) <= 0) {
                showError("Количество должно быть больше 0");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Некорректное количество");
            return false;
        }

        String priceText = priceTextField.getText().trim();
        if (priceText.isEmpty()) {
            showError("Введите цену");
            return false;
        }
        try {
            BigDecimal price = new BigDecimal(priceText);
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                showError("Цена не может быть отрицательной");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Некорректная цена");
            return false;
        }

        if (providerTextField.getText().trim().isEmpty()) {
            showError("Введите поставщика");
            return false;
        }

        if (cellTextField.getText().trim().isEmpty()) {
            showError("Введите ячейку хранения");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
    }

    private void clearFields() {
        quantityTextField.setText("");
        priceTextField.setText("");
        providerTextField.setText("");
        cellTextField.setText("");
        nameProductComboBox.setSelectedIndex(0);
        nameWarehouseComboBox.setSelectedIndex(0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        incomeHeaderPanel = new javax.swing.JPanel();
        namePanelLabel = new javax.swing.JLabel();
        incomeDateLabel = new javax.swing.JLabel();
        incomeContentPanel = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        nameProductComboBox = new javax.swing.JComboBox<>();
        nameWarehouseLabel = new javax.swing.JLabel();
        nameWarehouseComboBox = new javax.swing.JComboBox<>();
        quantityLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        providerLabel = new javax.swing.JLabel();
        providerTextField = new javax.swing.JTextField();
        cellLabel = new javax.swing.JLabel();
        cellTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 37, 41));
        setLayout(new java.awt.BorderLayout());

        // ===== ВЕРХНЯЯ ПАНЕЛЬ (ШАПКА) =====
        incomeHeaderPanel.setBackground(new java.awt.Color(33, 37, 41));
        incomeHeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        incomeHeaderPanel.setLayout(new java.awt.GridBagLayout());

        namePanelLabel.setFont(new java.awt.Font("Arial", 1, 20));
        namePanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        namePanelLabel.setText("Приход Товара");
        namePanelLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        incomeHeaderPanel.add(namePanelLabel, gridBagConstraints);

        incomeDateLabel.setFont(new java.awt.Font("Arial", 1, 20));
        incomeDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        incomeDateLabel.setText("время");
        incomeDateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        incomeHeaderPanel.add(incomeDateLabel, gridBagConstraints);

        add(incomeHeaderPanel, java.awt.BorderLayout.PAGE_START);

        // ===== ЦЕНТРАЛЬНАЯ ПАНЕЛЬ (ФОРМА) =====
        incomeContentPanel.setBackground(new java.awt.Color(33, 37, 41));
        incomeContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                null,
                "ОФОРМИТЬ ПРИХОД",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Arial", 1, 20),
                new java.awt.Color(255, 255, 255)
        ));
        incomeContentPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        incomeContentPanel.setLayout(new java.awt.GridBagLayout());

        // ===== СТРОКА 0: Товар =====
        productNameLabel.setFont(new java.awt.Font("Arial", 0, 18));
        productNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        productNameLabel.setText("Товар:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(productNameLabel, gridBagConstraints);

        nameProductComboBox.setFont(new java.awt.Font("Arial", 0, 18));
        nameProductComboBox.setForeground(new java.awt.Color(0, 0, 0));
        nameProductComboBox.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(nameProductComboBox, gridBagConstraints);

        // ===== СТРОКА 1: Склад =====
        nameWarehouseLabel.setFont(new java.awt.Font("Arial", 0, 18));
        nameWarehouseLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameWarehouseLabel.setText("Склад:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(nameWarehouseLabel, gridBagConstraints);

        nameWarehouseComboBox.setFont(new java.awt.Font("Arial", 0, 18));
        nameWarehouseComboBox.setForeground(new java.awt.Color(0, 0, 0));
        nameWarehouseComboBox.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(nameWarehouseComboBox, gridBagConstraints);

        // ===== СТРОКА 2: Количество =====
        quantityLabel.setFont(new java.awt.Font("Arial", 0, 18));
        quantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        quantityLabel.setText("Количество:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(quantityLabel, gridBagConstraints);

        quantityTextField.setFont(new java.awt.Font("Arial", 0, 18));
        quantityTextField.setForeground(new java.awt.Color(0, 0, 0));
        quantityTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(quantityTextField, gridBagConstraints);

        // ===== СТРОКА 3: Цена =====
        priceLabel.setFont(new java.awt.Font("Arial", 0, 18));
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Цена:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(priceLabel, gridBagConstraints);

        priceTextField.setFont(new java.awt.Font("Arial", 0, 18));
        priceTextField.setForeground(new java.awt.Color(0, 0, 0));
        priceTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(priceTextField, gridBagConstraints);

        // ===== СТРОКА 4: Поставщик =====
        providerLabel.setFont(new java.awt.Font("Arial", 0, 18));
        providerLabel.setForeground(new java.awt.Color(255, 255, 255));
        providerLabel.setText("Поставщик:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(providerLabel, gridBagConstraints);

        providerTextField.setFont(new java.awt.Font("Arial", 0, 18));
        providerTextField.setForeground(new java.awt.Color(0, 0, 0));
        providerTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(providerTextField, gridBagConstraints);

        // ===== СТРОКА 5: Ячейка =====
        cellLabel.setFont(new java.awt.Font("Arial", 0, 18));
        cellLabel.setForeground(new java.awt.Color(255, 255, 255));
        cellLabel.setText("Ячейка:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        incomeContentPanel.add(cellLabel, gridBagConstraints);

        cellTextField.setFont(new java.awt.Font("Arial", 0, 18));
        cellTextField.setForeground(new java.awt.Color(0, 0, 0));
        cellTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        incomeContentPanel.add(cellTextField, gridBagConstraints);

        // ===== СТРОКА 6: Кнопка =====
        addButton.setBackground(new java.awt.Color(40, 167, 69));
        addButton.setFont(new java.awt.Font("Arial", 1, 18));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Оформить");
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new java.awt.Dimension(300, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 30, 30);
        incomeContentPanel.add(addButton, gridBagConstraints);

        add(incomeContentPanel, java.awt.BorderLayout.CENTER);
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel cellLabel;
    private javax.swing.JTextField cellTextField;
    private javax.swing.JPanel incomeContentPanel;
    private javax.swing.JLabel incomeDateLabel;
    private javax.swing.JPanel incomeHeaderPanel;
    private javax.swing.JLabel namePanelLabel;
    private javax.swing.JComboBox<String> nameProductComboBox;
    private javax.swing.JComboBox<String> nameWarehouseComboBox;
    private javax.swing.JLabel nameWarehouseLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JLabel providerLabel;
    private javax.swing.JTextField providerTextField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    // End of variables declaration//GEN-END:variables
}
