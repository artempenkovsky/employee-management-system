package by.teachmeskills.transformers;

import by.teachmeskills.dto.VacancyRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.repository.EmployerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransformVacancyRequestToVacancy {
    public final EmployerRepository employerRepository;

    public TransformVacancyRequestToVacancy(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Vacancy transform (VacancyRequest vacancyRequest){
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(vacancyRequest.getTitle());
        vacancy.setActive(true);
        vacancy.setDescription(vacancyRequest.getDescription());
        Optional<Employer> optionalEmployer = employerRepository.findById(vacancyRequest.getEmployerId());
        Employer employer = optionalEmployer.orElse(new Employer());
        vacancy.setEmployer(employer);
        return vacancy;
    }
}
