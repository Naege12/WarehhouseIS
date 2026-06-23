/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panels;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author User
 */
public class productsPanel extends javax.swing.JPanel {
    final ProductDAO productDAO = new ProductDAO();
    LocalDate ld;

    /**
     * Creates new form productsPanel
     */
    public productsPanel() {
        initComponents();
        loadData();
        ld = LocalDate.now();
        productDateLabel.setText(ld.toString());
    }

    public void loadData()
    {
        loadProductData();
    }

    private void loadProductData() {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        try {
            List<Product> products = productDAO.getAllProducts();

            if (products.isEmpty()) {
                model.addRow(new Object[]{"—", "Нет товаров", "—", "—", "—", "—"});
            } else {
                for (Product p : products) {
                    model.addRow(new Object[]{
                            p.getId(),
                            p.getArticle(),
                            p.getName(),
                            p.getUnit(),
                            p.getCategory(),
                            p.getSupplierName(),
                            p.getPurchasePrice(),
                            p.getSellingPrice(),
                            p.getMinStock(),
                            p.isActive()
                    });
                }
            }
            System.out.println("✅ Загружено товаров: " + products.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditProductDialog(Product product) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Редактирование товара", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        // Форма
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtSku = new JTextField(product.getArticle());
        JTextField txtName = new JTextField(product.getName());
        JTextField txtCategory = new JTextField(product.getCategory());
        JTextField txtPurchasePrice = new JTextField(
                product.getPurchasePrice() != null ? product.getPurchasePrice().toString() : "0"
        );
        JTextField txtSellingPrice = new JTextField(
                product.getSellingPrice() != null ? product.getSellingPrice().toString() : "0"
        );
        JTextField txtMinStock = new JTextField(
                product.getMinStock() != null ? product.getMinStock().toString() : "0"
        );

        formPanel.add(new JLabel("Артикул (SKU):"));
        formPanel.add(txtSku);
        formPanel.add(new JLabel("Название:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Категория:"));
        formPanel.add(txtCategory);
        formPanel.add(new JLabel("Цена закупки:"));
        formPanel.add(txtPurchasePrice);
        formPanel.add(new JLabel("Цена продажи:"));
        formPanel.add(txtSellingPrice);
        formPanel.add(new JLabel("Мин. остаток:"));
        formPanel.add(txtMinStock);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Сохранить");
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);

        JButton btnCancel = new JButton("Отмена");
        btnCancel.setBackground(new Color(108, 117, 125));
        btnCancel.setForeground(Color.WHITE);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Обработчики
        btnCancel.addActionListener(e -> dialog.dispose());

        btnSave.addActionListener(e -> {
            try {
                // Валидация
                if (txtSku.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Артикул и название обязательны");
                    return;
                }

                // Обновляем объект
                product.setArticle(txtSku.getText().trim());
                product.setName(txtName.getText().trim());
                product.setCategory(txtCategory.getText().trim());
                product.setPurchasePrice(new BigDecimal(txtPurchasePrice.getText().trim()));
                product.setSellingPrice(new BigDecimal(txtSellingPrice.getText().trim()));
                product.setMinStock(new BigDecimal(txtMinStock.getText().trim()));

                // Сохраняем в БД
                boolean success = productDAO.updateProduct(product);
                if (success) {
                    dialog.dispose();
                    loadData(); // обновляем таблицу
                    JOptionPane.showMessageDialog(this, "Товар обновлён");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Ошибка обновления");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Проверьте числовые поля");
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

        productsHeaderPanel = new javax.swing.JPanel();
        namePanelLabel = new javax.swing.JLabel();
        productDateLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        reloadButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();

        setBackground(new java.awt.Color(33, 37, 41));
        setPreferredSize(new java.awt.Dimension(462, 936));
        setLayout(new java.awt.BorderLayout());

        productsHeaderPanel.setBackground(new java.awt.Color(33, 37, 41));
        productsHeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        productsHeaderPanel.setLayout(new java.awt.GridBagLayout());

        namePanelLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        namePanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        namePanelLabel.setText("Управление товарами");
        namePanelLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productsHeaderPanel.add(namePanelLabel, new java.awt.GridBagConstraints());

        productDateLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        productDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        productDateLabel.setText("время");
        productDateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productsHeaderPanel.add(productDateLabel, new java.awt.GridBagConstraints());

        add(productsHeaderPanel, java.awt.BorderLayout.PAGE_START);

        buttonPanel.setBackground(new java.awt.Color(33, 37, 41));
        buttonPanel.setMaximumSize(new java.awt.Dimension(900, 35));
        buttonPanel.setMinimumSize(new java.awt.Dimension(900, 35));
        buttonPanel.setPreferredSize(new java.awt.Dimension(900, 35));

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

        jButton2.setBackground(new java.awt.Color(255, 193, 7));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Редактировать");
        jButton2.setFocusPainted(false);
        jButton2.setMaximumSize(new java.awt.Dimension(190, 29));
        jButton2.setPreferredSize(new java.awt.Dimension(170, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttonPanel.add(jButton2);

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

        reloadButton.setBackground(new java.awt.Color(255, 153, 0));
        reloadButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        reloadButton.setForeground(new java.awt.Color(255, 255, 255));
        reloadButton.setText("Обновить");
        reloadButton.setFocusPainted(false);
        reloadButton.setPreferredSize(new java.awt.Dimension(130, 35));
        reloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(reloadButton);

        add(buttonPanel, java.awt.BorderLayout.CENTER);

        tablePanel.setBackground(new java.awt.Color(33, 37, 41));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ТОВАРЫ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        tablePanel.setPreferredSize(new java.awt.Dimension(900, 900));
        tablePanel.setLayout(new java.awt.BorderLayout());

        tableScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        tableScrollPane.setPreferredSize(new java.awt.Dimension(900, 700));

        tblProducts.setBackground(new java.awt.Color(255, 255, 255));
        tblProducts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProducts.setForeground(new java.awt.Color(33, 37, 41));
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Артикул", "Наименование", "Количество", "Категория", "Поствщик", "Цена покупки", "Цена продажи", "Минимаотный остаток", "Активен"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblProducts.setRowHeight(30);
        tableScrollPane.setViewportView(tblProducts);

        tablePanel.add(tableScrollPane, java.awt.BorderLayout.CENTER);

        add(tablePanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        AddProductForm addProductPanel = new AddProductForm();
        addProductPanel.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = tblProducts.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите товар для редактирования");
            return;
        }

        // Получаем ID из скрытой колонки
        Long productId = (Long) tblProducts.getValueAt(selectedRow, 0);

        // Загружаем товар из БД
        Product product = productDAO.getProductById(productId);
        if (product == null) {
            JOptionPane.showMessageDialog(this, "Товар не найден");
            return;
        }

        // Открываем диалог редактирования
        showEditProductDialog(product);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = tblProducts.getSelectedRow();
        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(this, "Выберите запись", "Внимание", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            Long id = (Long) tblProducts.getValueAt(selectedRow, 0);
            productDAO.deleteProduct(id);
            loadData();

        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void reloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Данные таблицы обновленны");
        loadData();
    }//GEN-LAST:event_reloadButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel namePanelLabel;
    private javax.swing.JLabel productDateLabel;
    private javax.swing.JPanel productsHeaderPanel;
    private javax.swing.JButton reloadButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}
