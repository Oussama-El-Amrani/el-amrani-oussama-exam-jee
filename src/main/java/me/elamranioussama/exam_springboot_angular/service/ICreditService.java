package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.entity.Credit;

import java.util.List;

public interface ICreditService {
    Credit getCreditById(Long id);

    List<Credit> getAllCredits();

    List<Credit> getCreditsByClientId(Long clientId);

    void deleteCredit(Long id);

    Credit updateCreditStatus(Long id, String status);
}
