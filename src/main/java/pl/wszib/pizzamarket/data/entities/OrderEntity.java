package pl.wszib.pizzamarket.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "pizza_name")
    private String pizzaName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_address_id")
    private OrderAddressEntity orderAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public OrderAddressEntity getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddressEntity orderAddress) {
        this.orderAddress = orderAddress;
    }
}


