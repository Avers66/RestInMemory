package avers66.restinmemory.web.controller.v1;

import avers66.restinmemory.dto.ClientRequestDto;
import avers66.restinmemory.dto.ClientResponseDto;
import avers66.restinmemory.dto.ListClientResponseDto;
import avers66.restinmemory.exception.EntityNotFoundException;
import avers66.restinmemory.mapper.v1.ClientMapper;
import avers66.restinmemory.model.Client;
import avers66.restinmemory.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ClientController
 *
 * @Author Tretyakov Alexandr
 */

@RequestMapping("/api/v1/clients")
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<ListClientResponseDto> findAll() {
       ListClientResponseDto listClientResponseDto =  clientMapper.listClientToListClientResponseDto(clientService.findAll());
       return ResponseEntity.ok(listClientResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long id) {
        ClientResponseDto clientResponseDto = clientMapper.clientToResponse(clientService.findById(id));
        return ResponseEntity.ok(clientResponseDto);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody @Valid ClientRequestDto requestDto) {
        Client client = clientService.save(clientMapper.requestToClient(requestDto));
        ClientResponseDto clientResponseDto = clientMapper.clientToResponse(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update(@RequestBody ClientRequestDto requestDto, @PathVariable Long id) {
        Client client = clientService.update(clientMapper.requestToClient(id, requestDto));
        ClientResponseDto clientResponseDto = clientMapper.clientToResponse(client);
        return ResponseEntity.ok(clientResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    Работает только для одного контроллера. Чтобы обрабатывать ошибки централизовано во всех контроллерах,
//    нужно использовать аннотацию @RestControllerAdvice

//    @ExceptionHandler(EntityNotFoundException.class) //Вместо этого метода можно сделать аннотацию над классом EntityNotFoundException
//    public ResponseEntity<Void> notFoundHandler(EntityNotFoundException ex) {
//        return ResponseEntity.notFound().build();
//    }
}
