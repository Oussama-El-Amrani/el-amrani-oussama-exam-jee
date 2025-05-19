package me.elamranioussama.exam_springboot_angular.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double montant;
    private String type; // Mensualité, Remboursement anticipé

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    // Getters and setters
}
