package dev.jeffersonfreitas.ecom_api.domain.model.order;

import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.OrderQuantity;

import java.math.BigDecimal;

public class OrderItem {

    private final Identity uuid;
    private final Identity productId;
    private final OrderQuantity quantity;
    private final BigDecimal value;
    private final BigDecimal total;

    public OrderItem(String productId, double quantity, BigDecimal value){
        this.uuid = new Identity();
        this.productId = new Identity(productId);
        this.quantity = new OrderQuantity(quantity);
        this.value = value;
        this.total = BigDecimal.valueOf(quantity).multiply(value);
    }

    public OrderItem(String uuid, String productId, double quantity, BigDecimal value, BigDecimal total){
        this.uuid = new Identity(uuid);
        this.productId = new Identity(productId);
        this.quantity = new OrderQuantity(quantity);
        this.value = value;
        this.total = total;
    }

    public BigDecimal total() {
        return total;
    }

    public Identity uuid(){
        return this.uuid;
    }

    public Identity productId(){
        return this.productId;
    }

    public OrderQuantity quantity() {
        return this.quantity;
    }

    public BigDecimal value(){
        return this.value;
    }
}
