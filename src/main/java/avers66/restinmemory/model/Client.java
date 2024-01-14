package avers66.restinmemory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Client
 *
 * @Author Tretyakov Alexandr
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
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
