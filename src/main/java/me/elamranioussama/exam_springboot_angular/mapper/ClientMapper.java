package me.elamranioussama.exam_springboot_angular.mapper;

import me.elamranioussama.exam_springboot_angular.dto.ClientDTO;
import me.elamranioussama.exam_springboot_angular.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements EntityMapper<ClientDTO, Client> {

    @Override
    public Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();
        // Only set ID if it's not a new entity (for updates)
        if (dto.getId() != null && dto.getId() > 0 && dto.getId() < Long.MAX_VALUE) {
            client.setId(dto.getId());
        }
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return client;
    }

    @Override
    public ClientDTO toDto(Client entity) {
        if (entity == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(entity.getId());
        clientDTO.setNom(entity.getNom());
        clientDTO.setEmail(entity.getEmail());
        return clientDTO;
    }
}
