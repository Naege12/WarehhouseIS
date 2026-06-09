package org.example.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.SplittableRandom;

public class Movement {
    public enum Type {
        IN,
        OUT,
        MOVE
    }

    private Long _id;
    private Type _type;
    private String _docNumber;
    private LocalDate _docDate;
    private Long _productId;
    private Long _warehouseId;
    private Long _userId;
    private BigDecimal _quantity;
    private BigDecimal _price;
    private String _fromCell;
    private String _toCell;
    private String _counterparty;
    private String _comment;
    private LocalDateTime _createdAt;

    public Movement() {
        _docDate = LocalDate.now();
        _createdAt = LocalDateTime.now();
    }

    public static Movement createIncoming(Long productId, Long warehouseId, Long userId, BigDecimal quantity, BigDecimal price, String toCell, String counterparty) {
        Movement m = new Movement();

        m._type = Type.IN;
        m._productId = productId;
        m._warehouseId = warehouseId;
        m._userId = userId;
        m._quantity = quantity;
        m._price = price;
        m._toCell = toCell;
        m._counterparty = counterparty;
        return m;
    }

    public static Movement createOutgoing(Long productId, Long warehouseId, Long userId, BigDecimal quantity, BigDecimal price, String fromCell, String counterparty) {
        Movement m = new Movement();

        m._type = Type.OUT;
        m._productId = productId;
        m._warehouseId = warehouseId;
        m._userId = userId;
        m._quantity = quantity;
        m._price = price;
        m._fromCell = fromCell;
        m._counterparty = counterparty;
        return m;
    }

    public static Movement createMove(Long productId, Long warehouseId, Long userId, BigDecimal quantity, BigDecimal price, String toCell, String fromCell, String comment) {
        Movement m = new Movement();

        m._type = Type.MOVE;
        m._productId = productId;
        m._warehouseId = warehouseId;
        m._userId = userId;
        m._quantity = quantity;
        m._price = price;
        m._toCell = toCell;
        m._fromCell = fromCell;
        m._comment = comment;
        return m;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    public String getDocNumber() {
        return _docNumber;
    }

    public void setDocNumber(String docNumber) {
        _docNumber = docNumber;
    }

    public Long getProductId() {
        return _productId;
    }

    public void setProductId(Long productId) {
        _productId = productId;
    }

    public Long getWarehouseId() {
        return _warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        _warehouseId = warehouseId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public BigDecimal getQuantity() {
        return _quantity;
    }

    public void setQuantity(BigDecimal quantity)
    {
        _quantity = quantity;
    }

    public BigDecimal getPrice()
    {
        return _price;
    }

    public void setPrice(BigDecimal price)
    {
        _price = price;
    }

    public String getFromCell()
    {
        return _fromCell;
    }

    public void setFromCell(String fromCell)
    {
        _fromCell = fromCell;
    }

    public String getToCell()
    {
        return _toCell;
    }

    public void setToCell(String toCell)
    {
        _toCell = toCell;
    }

    public String getCounterparty()
    {
        return _counterparty;
    }

    public void setCounterparty(String counterparty)
    {
        _counterparty = counterparty;
    }

    public String getComment()
    {
        return _comment;
    }

    public void setComment(String comment)
    {
        _comment = comment;
    }

    public LocalDateTime getCreatedAt()
    {
        return  _createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        _createdAt = createdAt;
    }
}
