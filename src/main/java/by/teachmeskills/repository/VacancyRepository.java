package by.teachmeskills.repository;

import by.teachmeskills.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByEmployerId(Long employerId);

    @Query(value = "select * from public.vacancies where employer_id=?1",nativeQuery = true)
    Page<Vacancy> findAllByEmployerId(Long employerId, Pageable pageable);
    @Query(value = "select * from public.vacancies where employer_id=?1 and active=true",nativeQuery = true)
    Page<Vacancy> findAllByEmployerIdAndStatusIsActive(Long employerId, Pageable pageable);

}