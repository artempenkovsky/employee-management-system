package by.teachmeskills.transformers;

import by.teachmeskills.dto.ResponseRequest;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.Response;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.repository.CandidateRepository;
import by.teachmeskills.repository.VacancyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TransformResponseRequestToResponse {
    private final CandidateRepository candidateRepository;
    private final VacancyRepository vacancyRepository;

    public TransformResponseRequestToResponse(CandidateRepository candidateRepository, VacancyRepository vacancyRepository) {
        this.candidateRepository = candidateRepository;
        this.vacancyRepository = vacancyRepository;
    }

    public Response transform(ResponseRequest responseRequest){
        Response response = new Response();
        Optional<Candidate> candidateOptional = candidateRepository.findById(responseRequest.getCandidateId());
        Candidate candidate = candidateOptional.orElse(new Candidate());
        response.setCandidate(candidate);
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(responseRequest.getVacancyId());
        Vacancy vacancy = optionalVacancy.orElse(new Vacancy());
        response.setVacancy(vacancy);
        response.setCoverLetter(responseRequest.getCoverLetter());
        response.setResponseTime(LocalDateTime.now());
        return response;
    }
}
