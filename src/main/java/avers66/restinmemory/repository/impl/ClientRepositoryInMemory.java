package avers66.restinmemory.repository.impl;

import avers66.restinmemory.exception.EntityNotFoundException;
import avers66.restinmemory.model.Client;
import avers66.restinmemory.model.Order;
import avers66.restinmemory.repository.ClientRepository;
import avers66.restinmemory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.Format;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * ClientRepositoryImpl
 *
 * @Author Tretyakov Alexandr
 */

@Repository
public class ClientRepositoryInMemory implements ClientRepository {


    OrderRepository orderRepository;

    private final Map<Long, Client> repository = new HashMap<>();

    private AtomicLong currentId = new AtomicLong(0);

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Client save(Client client) {
        Long id = currentId.incrementAndGet();
        client.setId(id);
        repository.put(id, client);
//        Instant createAt = Instant.now();
//        for (Order order : client.getOrderList()) {
//            order.setClientId(id);
//            order.setCreateAt(createAt);
//            orderRepository.save(order);
//        }
        return client;
    }

    @Override
    public Client update(Client client) {
        Client editClient = repository.get(client.getId());
        if (editClient == null) throw new EntityNotFoundException(MessageFormat.format("Клиент с Id {0} не найден", client.getId()));
        editClient.setName(client.getName());

//        repository.put(client.getId(), editClient);
        return editClient;

    }

    @Override
    public void deleteById(Long id) {
        Client client = repository.get(id);
        if (client == null) throw new EntityNotFoundException(MessageFormat.format("Клиент с Id {0} не найден", client.getId()));
        orderRepository.deleteByIDS(client.getOrderList().stream().map((c) -> c.getClientId()).collect(Collectors.toList()));
        repository.remove(id);

    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
        repository.clear();
        currentId.set(0);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
