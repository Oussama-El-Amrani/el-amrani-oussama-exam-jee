package me.elamranioussama.exam_springboot_angular.service;

import me.elamranioussama.exam_springboot_angular.dto.RemboursementDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IRemboursementService {

    List<RemboursementDTO> findAll();

    Optional<RemboursementDTO> findById(Long id);

    RemboursementDTO save(RemboursementDTO remboursementDTO);

    void delete(Long id);

    List<RemboursementDTO> findByCreditId(Long creditId);

    List<RemboursementDTO> findByDateBetween(LocalDate startDate, LocalDate endDate);

    double getTotalPaymentsByCreditId(Long creditId);

    double getRemainingAmountToPay(Long creditId);
}
