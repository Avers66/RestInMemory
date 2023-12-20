package avers66.restinmemory.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Client
 *
 * @Author Tretyakov Alexandr
 */

@Data
public class Client {

    private Long id;
    private String name;
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void remove(Order order) {
        orderList = orderList.stream().filter((o) -> !o.getId().equals(order.getId())).collect(Collectors.toList());
    }
}
