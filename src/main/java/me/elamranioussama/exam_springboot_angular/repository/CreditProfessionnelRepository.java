package me.elamranioussama.exam_springboot_angular.repository;

import me.elamranioussama.exam_springboot_angular.entity.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
}
