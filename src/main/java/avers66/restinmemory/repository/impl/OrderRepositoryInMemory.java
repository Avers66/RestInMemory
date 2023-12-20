package avers66.restinmemory.repository.impl;

import avers66.restinmemory.exception.EntityNotFoundException;
import avers66.restinmemory.model.Client;
import avers66.restinmemory.model.Order;
import avers66.restinmemory.repository.ClientRepository;
import avers66.restinmemory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * OrderRepositoryImpl
 *
 * @Author Tretyakov Alexandr
 */

@Repository
public class OrderRepositoryInMemory implements OrderRepository {

    private ClientRepository clientRepository;

    private final Map<Long, Order> repository = new HashMap<>();

    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Order save(Order order) {
        Long id = currentId.incrementAndGet();
        order.setId(id);
        order.setCreateAt(Instant.now());
        order.setUpdateAt(Instant.now());
        Client client = clientRepository.findById(order.getClientId())
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Клиент по Id {0} не найден", order.getClientId())));
        client.addOrder(order);
//        clientRepository.update(client);
        repository.put(id, order);
        return order;
    }

    @Override
    public Order update(Order order) {
        Order editOrder = repository.get(order.getId());
        if (editOrder == null) new RuntimeException(MessageFormat.format("Ордер Id {0} не найден", order.getId()));
        editOrder.setUpdateAt(Instant.now());
        editOrder.setCost(order.getCost());
        editOrder.setProduct(order.getProduct());
//        repository.put(order.getId(), editOrder);
        return editOrder;
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }

    @Override
    public void deleteByIDS(List<Long> ids) {
        ids.forEach(repository::remove);
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
