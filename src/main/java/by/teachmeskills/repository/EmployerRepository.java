package by.teachmeskills.repository;

import by.teachmeskills.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByUsername (String username);
}
