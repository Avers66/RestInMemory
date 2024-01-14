package avers66.restinmemory.web;

import avers66.restinmemory.StringTestUtils;
import avers66.restinmemory.dto.ClientResponseDto;
import avers66.restinmemory.dto.ListClientResponseDto;
import avers66.restinmemory.dto.OrderResponseDto;
import avers66.restinmemory.model.Client;
import avers66.restinmemory.model.Order;
import avers66.restinmemory.repository.impl.ClientRepositoryInMemory;
import avers66.restinmemory.repository.impl.OrderRepositoryInMemory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ClientControllerTestV2
 *
 * @Author Tretyakov Alexandr
 */

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTestV2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepositoryInMemory repository;

    @Autowired
    OrderRepositoryInMemory orderRepository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    public void whenFindAllThenReturnAllClients() throws Exception {
        Client client1 = new Client();
        client1.setName("Client 1");
        repository.save(client1);

        Client client2 = new Client();
        client2.setName("Client 2");
        repository.save(client2);

        Order order = new Order();
        order.setCost(new BigDecimal(100L));
        order.setProduct("Test product 1");
        order.setClientId(2L);
        orderRepository.save(order);

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_all_clients_response.json");

        String actualResponse = mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();


//        System.out.println(expectedResponse);
//        System.out.println(actualResponse);
//        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
//        Mockito.verify(repository, Mockito.times(1)).findAll();

    }


}
