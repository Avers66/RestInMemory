package avers66.restinmemory.repository;

import avers66.restinmemory.model.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ClientRepository
 *
 * @Author Tretyakov Alexandr
 */

public interface ClientRepository {



    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void deleteById(Long id);

    void deleteAll();



}
