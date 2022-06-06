package servlet;

import config.ControllerConfig;
import dao.OrderDao;
import lombok.SneakyThrows;
import mapper.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    @PostMapping("orders")
    public Order doPost(@RequestBody Order order) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        orderDao.saveOrder(order);

        return order;
    }

    @SneakyThrows
    @GetMapping("orders/{id}")
    public Order doGetById(@PathVariable Long id) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        return orderDao.getOrderById(id);
    }

    @SneakyThrows
    @GetMapping("orders")
    public List<Order> doGetAll() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        return orderDao.getAllOrders();
    }

    @SneakyThrows
    @GetMapping("version")
    public String version() {
        return "api version 1.0";
    }
}