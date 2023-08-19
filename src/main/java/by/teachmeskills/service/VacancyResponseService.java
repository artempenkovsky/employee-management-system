package by.teachmeskills.service;

import by.teachmeskills.dto.VacancyResponseRequest;
import by.teachmeskills.model.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyResponseService {
    Opportunity createVacancyResponse(VacancyResponseRequest request);
    List<Opportunity> getResponseByVacancy(Long vacancyId);

    List<Opportunity> getVacancyResponseByEmployer(Long employerId);

    Page<Opportunity> getVacancyResponseByEmployerWithPage(Long employerId, Pageable pageable);
}
