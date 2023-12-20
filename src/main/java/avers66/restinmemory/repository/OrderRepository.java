package avers66.restinmemory.repository;

import avers66.restinmemory.model.Client;
import avers66.restinmemory.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * OrderRepository
 *
 * @Author Tretyakov Alexandr
 */

public interface OrderRepository {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);

    Order update(Order order);

    void deleteById(Long id);

    void deleteByIDS(List<Long> ids);
}
