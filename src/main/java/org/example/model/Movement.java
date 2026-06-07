package org.example.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movement {
    public enum Type
    {
        IN,
        OUT,
        MOVE
    }

    private Long _id;
    private String _type;
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

    public Movement()
    {
        _docDate = LocalDate.now();
        _createdAt = LocalDateTime.now();
    }

    public static Movement createIncoming(Long productId, Long warehouseId, Long userId, BigDecimal quantity, BigDecimal price, String toCell, String counterparty)
    {

    }
}
