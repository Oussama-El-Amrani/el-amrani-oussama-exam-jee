package me.elamranioussama.exam_springboot_angular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private double montant;
    private String type;
    private Long creditId;
}
