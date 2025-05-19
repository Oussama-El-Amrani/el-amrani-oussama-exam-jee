package me.elamranioussama.exam_springboot_angular.mapper;

import me.elamranioussama.exam_springboot_angular.dto.*;
import me.elamranioussama.exam_springboot_angular.entity.*;
import me.elamranioussama.exam_springboot_angular.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    @Autowired
    private ClientRepository clientRepository;

    public CreditDTO toDto(Credit entity) {
        if (entity == null) {
            return null;
        }

        CreditDTO creditDTO;

        if (entity instanceof CreditPersonnel) {
            CreditPersonnelDTO dto = new CreditPersonnelDTO();
            dto.setMotif(((CreditPersonnel) entity).getMotif());
            creditDTO = dto;
        } else if (entity instanceof CreditImmobilier) {
            CreditImmobilierDTO dto = new CreditImmobilierDTO();
            dto.setTypeBien(((CreditImmobilier) entity).getTypeBien());
            creditDTO = dto;
        } else if (entity instanceof CreditProfessionnel) {
            CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
            dto.setMotif(((CreditProfessionnel) entity).getMotif());
            dto.setRaisonSociale(((CreditProfessionnel) entity).getRaisonSociale());
            creditDTO = dto;
        } else {
            creditDTO = new CreditDTO();
        }

        creditDTO.setId(entity.getId());
        creditDTO.setDateDemande(entity.getDateDemande());
        creditDTO.setStatut(entity.getStatut());
        creditDTO.setDateAcception(entity.getDateAcception());
        creditDTO.setMontant(entity.getMontant());
        creditDTO.setDureeRemboursement(entity.getDureeRemboursement());
        creditDTO.setTauxInteret(entity.getTauxInteret());

        if (entity.getClient() != null) {
            creditDTO.setClientId(entity.getClient().getId());
        }

        creditDTO.setCreditType(entity.getClass().getSimpleName());

        return creditDTO;
    }

    public Credit toEntity(CreditDTO dto) {
        if (dto == null) {
            return null;
        }

        Credit credit;

        if (dto instanceof CreditPersonnelDTO) {
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setMotif(((CreditPersonnelDTO) dto).getMotif());
            credit = creditPersonnel;
        } else if (dto instanceof CreditImmobilierDTO) {
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setTypeBien(((CreditImmobilierDTO) dto).getTypeBien());
            credit = creditImmobilier;
        } else if (dto instanceof CreditProfessionnelDTO) {
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setMotif(((CreditProfessionnelDTO) dto).getMotif());
            creditProfessionnel.setRaisonSociale(((CreditProfessionnelDTO) dto).getRaisonSociale());
            credit = creditProfessionnel;
        } else {
            throw new IllegalArgumentException("Unknown credit type");
        }

        // Only set ID if it's not a new entity (for updates)
        if (dto.getId() != null && dto.getId() > 0 && dto.getId() < Long.MAX_VALUE) {
            credit.setId(dto.getId());
        }
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcception(dto.getDateAcception());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());

        if (dto.getClientId() != null) {
            clientRepository.findById(dto.getClientId()).ifPresent(credit::setClient);
        }

        return credit;
    }
}
