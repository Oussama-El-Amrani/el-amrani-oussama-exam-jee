package me.elamranioussama.exam_springboot_angular.repository;

import me.elamranioussama.exam_springboot_angular.entity.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
