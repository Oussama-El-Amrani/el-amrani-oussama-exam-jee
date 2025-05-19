package me.elamranioussama.exam_springboot_angular;

import me.elamranioussama.exam_springboot_angular.entity.*;
import me.elamranioussama.exam_springboot_angular.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories(basePackages = "me.elamranioussama.exam_springboot_angular.repository")
@EntityScan(basePackages = "me.elamranioussama.exam_springboot_angular.entity")
@ComponentScan(basePackages = "me.elamranioussama.exam_springboot_angular")
public class ExamSpringbootAngularApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExamSpringbootAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
        ClientRepository clientRepository,
        CreditPersonnelRepository creditPersonnelRepository,
        CreditImmobilierRepository creditImmobilierRepository,
        CreditProfessionnelRepository creditProfessionnelRepository,
        RemboursementRepository remboursementRepository
    ) {
        return args -> {
            System.out.println("Initializing database with test data...");

            // Create clients
            Client client1 = new Client();
            client1.setNom("Oussama EL AMRANI");
            client1.setEmail("oussama@example.com");

            Client client2 = new Client();
            client2.setNom("Ahmed BENANI");
            client2.setEmail("ahmed@example.com");

            // Save clients
            clientRepository.saveAll(Arrays.asList(client1, client2));
            System.out.println("Clients saved successfully");

            // Create different types of credits
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setClient(client1);
            creditPersonnel.setDateDemande(LocalDate.now().minusMonths(2));
            creditPersonnel.setStatut("Accepté");
            creditPersonnel.setDateAcception(LocalDate.now().minusMonths(1));
            creditPersonnel.setMontant(50000);
            creditPersonnel.setDureeRemboursement(24);
            creditPersonnel.setTauxInteret(4.5);
            creditPersonnel.setMotif("Achat de voiture");

            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setClient(client1);
            creditImmobilier.setDateDemande(LocalDate.now().minusMonths(6));
            creditImmobilier.setStatut("Accepté");
            creditImmobilier.setDateAcception(LocalDate.now().minusMonths(5));
            creditImmobilier.setMontant(300000);
            creditImmobilier.setDureeRemboursement(240);
            creditImmobilier.setTauxInteret(3.2);
            creditImmobilier.setTypeBien("Appartement");

            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setClient(client2);
            creditProfessionnel.setDateDemande(LocalDate.now().minusMonths(3));
            creditProfessionnel.setStatut("En cours");
            creditProfessionnel.setMontant(150000);
            creditProfessionnel.setDureeRemboursement(60);
            creditProfessionnel.setTauxInteret(5.0);
            creditProfessionnel.setMotif("Expansion d'entreprise");
            creditProfessionnel.setRaisonSociale("Tech Solutions SARL");

            // Save credits
            creditPersonnelRepository.save(creditPersonnel);
            creditImmobilierRepository.save(creditImmobilier);
            creditProfessionnelRepository.save(creditProfessionnel);
            System.out.println("Credits saved successfully");

            // Create remboursements
            Remboursement remboursement1 = new Remboursement();
            remboursement1.setCredit(creditPersonnel);
            remboursement1.setDate(LocalDate.now().minusDays(15));
            remboursement1.setMontant(2200);
            remboursement1.setType("Mensualité");

            Remboursement remboursement2 = new Remboursement();
            remboursement2.setCredit(creditImmobilier);
            remboursement2.setDate(LocalDate.now().minusDays(15));
            remboursement2.setMontant(1500);
            remboursement2.setType("Mensualité");

            Remboursement remboursement3 = new Remboursement();
            remboursement3.setCredit(creditImmobilier);
            remboursement3.setDate(LocalDate.now().minusDays(45));
            remboursement3.setMontant(1500);
            remboursement3.setType("Mensualité");

            Remboursement remboursement4 = new Remboursement();
            remboursement4.setCredit(creditImmobilier);
            remboursement4.setDate(LocalDate.now().minusDays(5));
            remboursement4.setMontant(10000);
            remboursement4.setType("Remboursement anticipé");

            // Save remboursements
            remboursementRepository.saveAll(Arrays.asList(remboursement1, remboursement2, remboursement3, remboursement4));
            System.out.println("Remboursements saved successfully");

            System.out.println("Database initialization completed!");
        };
    }
}

