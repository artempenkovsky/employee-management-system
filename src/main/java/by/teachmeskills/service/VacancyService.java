package by.teachmeskills.service;

import by.teachmeskills.dto.VacancyRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyService {
    List<Vacancy> getAllVacancies();

    Vacancy createVacancy(VacancyRequest request);

    List<Vacancy> getVacancyByEmployer(Long employerId);

    List<Vacancy> getActiveVacancyByEmployer(Long employerId);

    Vacancy changeVacancyStatus(Long vacancyId);
    Page<Vacancy> getAllVacancy(Pageable pageable);
    Page<Vacancy> getAllVacancyByEmployer(Long employerId, Pageable pageable);
    Page<Vacancy> getAllByEmployerIdAndStatusIsActive(Long employerId, Pageable pageable);

    Employer getEmployerByVacancyId(Long vacancyId);
}
