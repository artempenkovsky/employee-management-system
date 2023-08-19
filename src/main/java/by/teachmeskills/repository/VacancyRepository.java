package by.teachmeskills.repository;

import by.teachmeskills.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByEmployerId(Long employerId);

    Page<Vacancy> findAllByEmployerId(Long employerId, Pageable pageable);

    Page<Vacancy> findAllByEmployerIdAndActiveIsTrue(Long employerId, Pageable pageable);
    List<Vacancy> findAllByEmployerIdAndActiveIsTrue(Long employerId);

}