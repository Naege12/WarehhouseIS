package controllers;


import model.Product;
import model.User;
import model.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Controller {

    public boolean checkAccept(String login, String password)
    {
        return !login.isEmpty() && !password.isEmpty();
    }

    public boolean addProductCheckAccept(String article, String name, String unit, String category,
                                         String supplierName, String purchasePrice, String sellingPrice,
                                         String storageCell, String minStock) {

        return article.isEmpty() || name.isEmpty() || unit.isEmpty() ||
                category.isEmpty() || supplierName.isEmpty() || purchasePrice.isEmpty() ||
                sellingPrice.isEmpty() || storageCell.isEmpty() || minStock.isEmpty();
    }

    public boolean addWarehouseCheckAccept(String name, String address)
    {
        return name.isEmpty() || address.isEmpty();
    }

    public boolean addUserCheckAccept(String login, String password, String name, String role)
    {
        return login.isEmpty() || password.isEmpty() || name.isEmpty() || role.isEmpty();
    }

    public Product addNewProduct(String article, String name, String unit, String category, String supplierName, String purchasePrice, String sellingPrice, String storageCell, String minStock)
    {
        Product p = new Product();
        p.setArticle(article);
        p.setName(name);
        p.setUnit(unit);
        p.setCategory(category);
        p.setSupplierName(supplierName);
        p.setPurchasePrice(new BigDecimal(purchasePrice));
        p.setSellingPrice(new BigDecimal(sellingPrice));
        p.setStorageCell(storageCell);
        p.setMinStock(new BigDecimal(minStock));

        return p;
    }

    public Warehouse addNewWarehouse(String name, String address)
    {
        Warehouse w = new Warehouse();
        w.setName(name);
        w.setAddress(address);

        return w;
    }

    public User addNewUser(String login, String password, String name, String role)
    {
        LocalDateTime ldt;
        ldt = LocalDateTime.now();
        User u = new User();
        u.setLogin(login);
        u.setPassword(password);
        u.setFullName(name);
        if(role.equals("Администратор"))
        {
            role = "admin";
        }
        else if(role.equals("Пользователь"))
        {
            role = "user";
        }

        u.setRole(role);
        u.setMustChangePassword(true);
        u.setCreatedAt(ldt);

        return u;
    }
}
