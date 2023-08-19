package by.teachmeskills.repository;

import by.teachmeskills.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyResponseRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByVacancyId(Long vacancyId);
    @Query("SELECT r  FROM responses r WHERE r.vacancy.employer.id = ?1")
    List<Opportunity> findVacancyResponseByEmployer(Long employerId);
    @Query("SELECT r  FROM responses r WHERE r.vacancy.employer.id = ?1 AND r.vacancy.id = ?2")
    List<Opportunity> findVacancyResponseByEmployerAndVacancy (Long employerId, Long vacancyId);
}
