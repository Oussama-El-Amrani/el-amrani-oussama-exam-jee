package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.entity.Client;

import java.util.List;

public interface IClientService {
    Client saveClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();

    Client updateClient(Client client);

    void deleteClient(Long id);

    List<Client> getClientsWithCredits();
}
