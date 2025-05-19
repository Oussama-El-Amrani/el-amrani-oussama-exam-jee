package me.elamranioussama.exam_springboot_angular.controller;

import me.elamranioussama.exam_springboot_angular.dto.ClientDTO;
import me.elamranioussama.exam_springboot_angular.entity.Client;
import me.elamranioussama.exam_springboot_angular.mapper.ClientMapper;
import me.elamranioussama.exam_springboot_angular.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper mapper;

    public ClientController(IClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(clientService.getClientById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {

        return new ResponseEntity<>(
            mapper.toDto(
                clientService.saveClient(mapper.toEntity(dto)
                )
            ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO client) {
        client.setId(id);
        return ResponseEntity.ok(mapper.toDto(clientService.updateClient(
            mapper.toEntity(client)
        )));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/with-credits")
    public ResponseEntity<List<ClientDTO>> getClientsWithCredits() {
        return ResponseEntity.ok(clientService.getClientsWithCredits().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));
    }
}
