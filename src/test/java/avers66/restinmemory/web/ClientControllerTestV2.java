package avers66.restinmemory.web;
import avers66.restinmemory.StringTestUtils;
import avers66.restinmemory.dto.ClientRequestDto;
import avers66.restinmemory.model.Client;
import avers66.restinmemory.model.Order;
import avers66.restinmemory.repository.impl.ClientRepositoryInMemory;
import avers66.restinmemory.repository.impl.OrderRepositoryInMemory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
                .andDo(print())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();


//        System.out.println(expectedResponse);
//        System.out.println(actualResponse);
//        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
//        Mockito.verify(repository, Mockito.times(1)).findAll();

    }

    @Test
    public void whenFindByIdThenReturnClient() throws Exception {
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

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_by_id_clients_response.json");

        String actualResponse = mockMvc.perform(get("/api/v1/clients/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();

//        System.out.println(expectedResponse);
//        System.out.println(actualResponse);
    }

    @Test
    public void whenSaveThenReturnClient() throws Exception {
        ClientRequestDto clientRequest = new ClientRequestDto("Client 1");
        String expectedResponse = StringTestUtils.readStringFromResource("response/save_clients_response.json");

        String actualResponse = mockMvc.perform(post("/api/v1/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(clientRequest)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();

//        System.out.println(expectedResponse);
//        System.out.println(actualResponse);
    }

    @Test
    public void whenUpdateThenReturnClient() throws Exception {
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

        String expectedResponse = StringTestUtils.readStringFromResource("response/update_clients_response.json");

        String actualResponse = mockMvc.perform(put("/api/v1/clients/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"Client 12\"}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();

//        System.out.println(expectedResponse);
//        System.out.println(actualResponse);
    }

    @Test
    public void whenDeleteThenReturnClient() throws Exception {
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

        mockMvc.perform(delete("/api/v1/clients/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

    }


}
