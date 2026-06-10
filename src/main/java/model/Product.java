package model;

import java.math.BigDecimal;

public class Product {
    private Long _id;
    private String _article;
    private String _name;
    private String _unit;
    private String _category;
    private String _supplierName;
    private BigDecimal _purchasePrice;
    private BigDecimal _sellingPrice;
    private String _storageCell;
    private BigDecimal _minStock;
    private boolean _isActive;

    public Product() {}

    public Product(String article, String name, String category, String supplierName,
                   BigDecimal purchasePrice, BigDecimal sellingPrice, String unit)
    {
        _article = article;
        _name = name;
        _category = category;
        _supplierName = supplierName;
        _purchasePrice = purchasePrice;
        _sellingPrice = sellingPrice;
        _unit = unit;
        _isActive = true;
        _minStock = BigDecimal.ZERO;
    }

    public Long getId()
    {
        return _id;
    }

    public void setId(Long id)
    {
        _id = id;
    }

    public String getArticle()
    {
        return _article;
    }

    public void setArticle(String article)
    {
        _article = article;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getCategory()
    {
        return _category;
    }

    public void setCategory(String category)
    {
        _category = category;
    }

    public String getSupplierName()
    {
        return _supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        _supplierName = supplierName;
    }

    public String getUnit()
    {
        return _unit;
    }

    public void setUnit(String unit)
    {
        _unit = unit;
    }

    public BigDecimal getPurchasePrice()
    {
        return _purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice)
    {
        _purchasePrice = purchasePrice;
    }

    public BigDecimal getSellingPrice()
    {
        return _sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice)
    {
        _sellingPrice = sellingPrice;
    }

    public String getStorageCell()
    {
        return _storageCell;
    }

    public void setStorageCell(String storageCell)
    {
        _storageCell = storageCell;
    }

    public BigDecimal getMinStock()
    {
        return _minStock;
    }

    public void setMinStock(BigDecimal minStock)
    {
        _minStock = minStock;
    }

    public boolean isActive()
    {
        return _isActive;
    }

    public void setIsActive(boolean isActive)
    {

        _isActive = isActive;
    }

    public boolean isLowStock(BigDecimal currentStock) {
        return currentStock.compareTo(_minStock) < 0;
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, sku='%s', name='%s', price=%.2f}",
                _id, _article, _name, _sellingPrice);
    }
}
