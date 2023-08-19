package by.teachmeskills.service.impl;

import by.teachmeskills.dto.VacancyResponseRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Opportunity;
import by.teachmeskills.repository.EmployerRepository;
import by.teachmeskills.repository.VacancyResponseRepository;
import by.teachmeskills.service.VacancyResponseService;
import by.teachmeskills.transformers.TransformResponseRequestToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class VacancyResponseServiceImpl implements VacancyResponseService {
    private final TransformResponseRequestToResponse transformer;
    private final VacancyResponseRepository vacancyResponseRepository;
    private final EmployerRepository employerRepository;

    public VacancyResponseServiceImpl(TransformResponseRequestToResponse transformer,
                                      VacancyResponseRepository vacancyResponseRepository, EmployerRepository employerRepository) {
        this.transformer = transformer;
        this.vacancyResponseRepository = vacancyResponseRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public Opportunity createVacancyResponse(VacancyResponseRequest request) {
        Opportunity vacancyOpportunity = transformer.transform(request);
        Opportunity save = vacancyResponseRepository.save(vacancyOpportunity);
        return save;
    }

    @Override
    public List<Opportunity> getResponseByVacancy(Long vacancyId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Employer> username = employerRepository.findByUsername(name);
        if (username.isPresent()) {
            List<Opportunity> vacancyOpportunityByEmployerAndVacancy = vacancyResponseRepository.findVacancyResponseByEmployerAndVacancy(username.get().getId(), vacancyId);
            return vacancyOpportunityByEmployerAndVacancy;
        } else {
            return List.of();
        }

    }

    @Override
    public List<Opportunity> getVacancyResponseByEmployer(Long employerId) {
        return vacancyResponseRepository.findVacancyResponseByEmployer(employerId);
    }

    @Override
    public Page<Opportunity> getVacancyResponseByEmployerWithPage(Long employerId, Pageable pageable) {
        List<Opportunity> vacancyOpportunityList = getVacancyResponseByEmployer(employerId);
        Page<Opportunity> responsePage = new PageImpl<Opportunity>(vacancyOpportunityList, pageable, vacancyOpportunityList.size());
        return responsePage;
    }
}
