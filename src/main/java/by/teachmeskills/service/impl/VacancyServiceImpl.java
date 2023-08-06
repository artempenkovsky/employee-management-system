package by.teachmeskills.service.impl;

import by.teachmeskills.dto.VacancyRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.repository.VacancyRepository;
import by.teachmeskills.service.VacancyService;
import by.teachmeskills.transformers.TransformVacancyRequestToVacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final TransformVacancyRequestToVacancy transformer;

    public VacancyServiceImpl(VacancyRepository vacancyRepository, TransformVacancyRequestToVacancy transformer) {
        this.vacancyRepository = vacancyRepository;
        this.transformer = transformer;
    }

    @Override
    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    @Override
    public Vacancy createVacancy(VacancyRequest request) {
        Vacancy vacancy = transformer.transform(request);
        Vacancy save = vacancyRepository.save(vacancy);
        return save;
    }

    @Override
    public List<Vacancy> getVacancyByEmployer(Long employerId) {
        List<Vacancy> byEmployerId = vacancyRepository.findByEmployerId(employerId);
        return byEmployerId;
    }

    @Override
    public List<Vacancy> getActiveVacancyByEmployer(Long employerId) {
        return getVacancyByEmployer(employerId)
                .stream()
                .filter(vacancy -> vacancy.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy changeVacancyStatus(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        if (vacancy != null) {
            vacancy.setActive(!vacancy.isActive());
            return vacancyRepository.save(vacancy);
        }
        return null;
    }

    @Override
    public Page<Vacancy> getAllVacancy(Pageable pageable) {
        Page<Vacancy> vacancyPage = vacancyRepository.findAll(pageable);
        return vacancyPage;
    }

    @Override
    public Page<Vacancy> getAllVacancyByEmployer(Long employerId, Pageable pageable) {
        Page<Vacancy> vacancyPage = vacancyRepository.findAllByEmployerId(employerId, pageable);
        return vacancyPage;
    }

    @Override
    public Page<Vacancy> getAllByEmployerIdAndStatusIsActive(Long employerId, Pageable pageable) {
        Page<Vacancy> vacancyPage = vacancyRepository.findAllByEmployerIdAndStatusIsActive(employerId, pageable);
        return vacancyPage;
    }

    @Override
    public Employer getEmployerByVacancyId(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        if(vacancy !=null){
            return vacancy.getEmployer();
        }
        return null;
    }
}
