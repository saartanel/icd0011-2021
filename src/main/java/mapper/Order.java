package mapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @SequenceGenerator(name="order_seq", sequenceName = "SEQ1", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private long id;

    @NotNull
    private String orderNumber;

    @ElementCollection
    @CollectionTable(name="order_rows")
    private List<OrderRows> orderRows;

    public Order() {
        super();
    }

    public Order(long id, String orderNumber, List<OrderRows> orderRows) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderRows = orderRows;
    }

    public long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public List<OrderRows> getOrderRows() {
        return orderRows;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderRows(List<OrderRows> orderRows) {
        this.orderRows = orderRows;
    }

    public void addOrderRow(OrderRows orderRow) {
        orderRows.add(orderRow);
    }
}
