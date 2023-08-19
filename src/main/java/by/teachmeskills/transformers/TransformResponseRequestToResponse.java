package by.teachmeskills.transformers;

import by.teachmeskills.dto.VacancyResponseRequest;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.model.Opportunity;
import by.teachmeskills.repository.CandidateRepository;
import by.teachmeskills.repository.VacancyRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TransformResponseRequestToResponse {
    private final CandidateRepository candidateRepository;
    private final VacancyRepository vacancyRepository;

    public TransformResponseRequestToResponse(CandidateRepository candidateRepository, VacancyRepository vacancyRepository) {
        this.candidateRepository = candidateRepository;
        this.vacancyRepository = vacancyRepository;
    }

    public Opportunity transform(VacancyResponseRequest vacancyResponseRequest){
        Opportunity vacancyOpportunity = new Opportunity();
        Optional<Candidate> candidateOptional = candidateRepository.findById(vacancyResponseRequest.getCandidateId());
        Candidate candidate = candidateOptional.orElse(new Candidate());
        vacancyOpportunity.setCandidate(candidate);
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(vacancyResponseRequest.getVacancyId());
        Vacancy vacancy = optionalVacancy.orElse(new Vacancy());
        vacancyOpportunity.setVacancy(vacancy);
        vacancyOpportunity.setCoverLetter(vacancyResponseRequest.getCoverLetter());
        vacancyOpportunity.setResponseTime(Instant.now());
        return vacancyOpportunity;
    }
}
