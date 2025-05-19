package me.elamranioussama.exam_springboot_angular.service.impl;

import me.elamranioussama.exam_springboot_angular.entity.Client;
import me.elamranioussama.exam_springboot_angular.repository.ClientRepository;
import me.elamranioussama.exam_springboot_angular.service.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        // Check if client exists
        getClientById(client.getId());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        // Check if client exists
        getClientById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getClientsWithCredits() {
        return clientRepository.findAll().stream()
                .filter(client -> client.getCredits() != null && !client.getCredits().isEmpty())
                .collect(Collectors.toList());
    }
}