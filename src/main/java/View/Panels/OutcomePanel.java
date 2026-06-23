package View.Panels;

import dao.MovementDAO;
import dao.ProductDAO;
import dao.WarehouseDAO;
import model.Movement;
import model.Product;
import model.User;
import model.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Панель для оформления расхода товара со склада
 *
 * @author User
 */
public class OutcomePanel extends javax.swing.JPanel {
    private MovementDAO movementDAO = new MovementDAO();
    private ProductDAO productDAO = new ProductDAO();
    private WarehouseDAO warehouseDAO = new WarehouseDAO();
    private User currentUser;

    public OutcomePanel() {
        initComponents();
        updateDateTime();
        loadComboBoxes();
    }


    public OutcomePanel(User user) {
        this();
        this.currentUser = user;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        outcomeHeaderPanel = new javax.swing.JPanel();
        namePanelLabel = new javax.swing.JLabel();
        incomeDateLabel = new javax.swing.JLabel();

        outcomeContentPanel = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        nameProductComboBox = new javax.swing.JComboBox<>();
        nameWarehouseLabel = new javax.swing.JLabel();
        nameWarehouseComboBox = new javax.swing.JComboBox<>();
        quantityLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        clientLabel = new javax.swing.JLabel();
        clientTextField = new javax.swing.JTextField();
        cellLabel = new javax.swing.JLabel();
        cellTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 37, 41));
        setLayout(new java.awt.BorderLayout());
        outcomeHeaderPanel.setBackground(new java.awt.Color(33, 37, 41));
        outcomeHeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        outcomeHeaderPanel.setLayout(new java.awt.GridBagLayout());

        namePanelLabel.setFont(new java.awt.Font("Arial", 1, 20));
        namePanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        namePanelLabel.setText("Расход товара");
        namePanelLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        outcomeHeaderPanel.add(namePanelLabel, gridBagConstraints);

        incomeDateLabel.setFont(new java.awt.Font("Arial", 1, 20));
        incomeDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        incomeDateLabel.setText("время");
        incomeDateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        outcomeHeaderPanel.add(incomeDateLabel, gridBagConstraints);

        add(outcomeHeaderPanel, java.awt.BorderLayout.PAGE_START);

        outcomeContentPanel.setBackground(new java.awt.Color(33, 37, 41));
        outcomeContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
                null,
                "ОФОРМИТЬ РАСХОД",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Arial", 1, 20),
                new java.awt.Color(255, 255, 255)
        ));
        outcomeContentPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        outcomeContentPanel.setLayout(new java.awt.GridBagLayout());

        productNameLabel.setFont(new java.awt.Font("Arial", 0, 18));
        productNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        productNameLabel.setText("Товар:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(productNameLabel, gridBagConstraints);

        nameProductComboBox.setFont(new java.awt.Font("Arial", 0, 18));
        nameProductComboBox.setForeground(new java.awt.Color(0, 0, 0));
        nameProductComboBox.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(nameProductComboBox, gridBagConstraints);

        nameWarehouseLabel.setFont(new java.awt.Font("Arial", 0, 18));
        nameWarehouseLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameWarehouseLabel.setText("Склад:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(nameWarehouseLabel, gridBagConstraints);

        nameWarehouseComboBox.setFont(new java.awt.Font("Arial", 0, 18));
        nameWarehouseComboBox.setForeground(new java.awt.Color(0, 0, 0));
        nameWarehouseComboBox.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(nameWarehouseComboBox, gridBagConstraints);

        quantityLabel.setFont(new java.awt.Font("Arial", 0, 18));
        quantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        quantityLabel.setText("Количество:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(quantityLabel, gridBagConstraints);

        quantityTextField.setFont(new java.awt.Font("Arial", 0, 18));
        quantityTextField.setForeground(new java.awt.Color(0, 0, 0));
        quantityTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(quantityTextField, gridBagConstraints);

        priceLabel.setFont(new java.awt.Font("Arial", 0, 18));
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Цена:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(priceLabel, gridBagConstraints);

        priceTextField.setFont(new java.awt.Font("Arial", 0, 18));
        priceTextField.setForeground(new java.awt.Color(0, 0, 0));
        priceTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(priceTextField, gridBagConstraints);

        clientLabel.setFont(new java.awt.Font("Arial", 0, 18));
        clientLabel.setForeground(new java.awt.Color(255, 255, 255));
        clientLabel.setText("Клиент:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(clientLabel, gridBagConstraints);

        clientTextField.setFont(new java.awt.Font("Arial", 0, 18));
        clientTextField.setForeground(new java.awt.Color(0, 0, 0));
        clientTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(clientTextField, gridBagConstraints);

        cellLabel.setFont(new java.awt.Font("Arial", 0, 18));
        cellLabel.setForeground(new java.awt.Color(255, 255, 255));
        cellLabel.setText("Ячейка:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 10);
        outcomeContentPanel.add(cellLabel, gridBagConstraints);

        cellTextField.setFont(new java.awt.Font("Arial", 0, 18));
        cellTextField.setForeground(new java.awt.Color(0, 0, 0));
        cellTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 30);
        outcomeContentPanel.add(cellTextField, gridBagConstraints);

        addButton.setBackground(new java.awt.Color(40, 167, 69));
        addButton.setFont(new java.awt.Font("Arial", 1, 18));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Оформить");
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new java.awt.Dimension(300, 45));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 30, 30);
        outcomeContentPanel.add(addButton, gridBagConstraints);

        // Добавляем панель в центр
        add(outcomeContentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>


    private void updateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        incomeDateLabel.setText(LocalDateTime.now().format(formatter));
    }

    private void loadComboBoxes() {
        nameProductComboBox.removeAllItems();
        nameWarehouseComboBox.removeAllItems();

        try {
            List<Product> products = productDAO.getAllProducts();
            for (Product p : products) {
                nameProductComboBox.addItem(p.getId() + " - " + p.getName());
            }
            System.out.println("✅ Загружено товаров: " + products.size());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка загрузки товаров");
        }

        try {
            List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
            for (Warehouse w : warehouses) {
                nameWarehouseComboBox.addItem(w.getId() + " - " + w.getName());
            }
            System.out.println("✅ Загружено складов: " + warehouses.size());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка загрузки складов");
        }
    }

    private void clearFields() {
        quantityTextField.setText("");
        priceTextField.setText("");
        clientTextField.setText("");
        cellTextField.setText("");
        if (nameProductComboBox.getItemCount() > 0) {
            nameProductComboBox.setSelectedIndex(0);
        }
        if (nameWarehouseComboBox.getItemCount() > 0) {
            nameWarehouseComboBox.setSelectedIndex(0);
        }
        updateDateTime();
    }


    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
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

        String qtyText = quantityTextField.getText().trim();
        if (qtyText.isEmpty()) {
            showError("Введите количество");
            return false;
        }
        try {
            BigDecimal qty = new BigDecimal(qtyText);
            if (qty.compareTo(BigDecimal.ZERO) <= 0) {
                showError("Количество должно быть больше 0");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Некорректное количество (должно быть число)");
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
            showError("Некорректная цена (должно быть число)");
            return false;
        }

        if (clientTextField.getText().trim().isEmpty()) {
            showError("Введите клиента");
            return false;
        }

        if (cellTextField.getText().trim().isEmpty()) {
            showError("Введите ячейку хранения");
            return false;
        }

        return true;
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("🔘 Нажата кнопка 'Оформить расход'");

        if (!validateFields()) {
            return;
        }

        try {
            String productStr = (String) nameProductComboBox.getSelectedItem();
            Long productId = Long.parseLong(productStr.split(" - ")[0]);
            System.out.println("📦 Товар ID: " + productId);

            String warehouseStr = (String) nameWarehouseComboBox.getSelectedItem();
            Long warehouseId = Long.parseLong(warehouseStr.split(" - ")[0]);
            System.out.println("🏢 Склад ID: " + warehouseId);

            BigDecimal quantity = new BigDecimal(quantityTextField.getText().trim());
            BigDecimal price = new BigDecimal(priceTextField.getText().trim());
            String client = clientTextField.getText().trim();
            String cell = cellTextField.getText().trim();

            System.out.println("📊 Количество: " + quantity);
            System.out.println("💰 Цена: " + price);
            System.out.println("👤 Клиент: " + client);
            System.out.println("📍 Ячейка: " + cell);

            BigDecimal currentStock = movementDAO.getCurrentStock(productId, warehouseId);
            System.out.println("📦 Текущий остаток: " + currentStock);

            if (currentStock.compareTo(quantity) < 0) {
                JOptionPane.showMessageDialog(this,
                        "❌ Недостаточно товара на складе!\n\n" +
                                "Доступно: " + currentStock + "\n" +
                                "Запрашиваемо: " + quantity + "\n\n" +
                                "Уменьшите количество или пополните склад.",
                        "Ошибка остатка",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }



            Movement movement = new Movement();
            movement.setType(Movement.Type.OUT);
            movement.setDocNumber("OUT-" + System.currentTimeMillis());
            movement.setDocDate(LocalDate.now());
            movement.setProductId(productId);
            movement.setWarehouseId(warehouseId);
            movement.setUserId(currentUser != null ? currentUser.getId() : 1L);
            movement.setQuantity(quantity);
            movement.setPrice(price);
            movement.setFromCell(cell);
            movement.setCounterparty(client);
            movement.setComment("Расход клиенту " + client);

            System.out.println("📝 Создано движение: " + movement.getDocNumber());

            boolean success = movementDAO.saveMovement(movement);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "✅ Расход оформлен успешно!\n\n" +
                                "Номер: " + movement.getDocNumber() + "\n" +
                                "Товар: " + productStr + "\n" +
                                "Количество: " + quantity + "\n" +
                                "Сумма: " + quantity.multiply(price) + " ₽\n" +
                                "Клиент: " + client,
                        "Успех",
                        JOptionPane.INFORMATION_MESSAGE
                );
                clearFields();
                updateDashboard();

            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Ошибка при сохранении расхода в базу данных.\n" +
                                "Проверьте подключение к БД и попробуйте снова.",
                        "Ошибка БД",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            showError("Проверьте правильность числовых полей");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "❌ Непредвиденная ошибка:\n" + ex.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void updateDashboard() {
        java.awt.Container parent = getParent();
        while (parent != null) {
            if (parent instanceof View.MainForm) {
                View.MainForm mainForm = (View.MainForm) parent;

                // Ищем панель дашборда среди всех компонентов
                for (java.awt.Component comp : mainForm.getContentPane().getComponents()) {
                    if (comp instanceof View.Panels.dashboardPanel) {
                        System.out.println("🔄 Обновление дашборда...");
                        ((View.Panels.dashboardPanel) comp).refreshData();
                        break;
                    }
                }
                break;
            }
            parent = parent.getParent();
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JLabel cellLabel;
    private javax.swing.JTextField cellTextField;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JTextField clientTextField;
    private javax.swing.JLabel incomeDateLabel;
    private javax.swing.JLabel namePanelLabel;
    private javax.swing.JComboBox<String> nameProductComboBox;
    private javax.swing.JComboBox<String> nameWarehouseComboBox;
    private javax.swing.JLabel nameWarehouseLabel;
    private javax.swing.JPanel outcomeContentPanel;
    private javax.swing.JPanel outcomeHeaderPanel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    // End of variables declaration
}