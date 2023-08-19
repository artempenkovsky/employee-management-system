package by.teachmeskills.dto;

import lombok.Data;

import java.time.Instant;
@Data

public class OpportunityResponse {
    private Long id;
    private CandidateResponse candidateResponse;
    private VacancyResponseDTO vacancyResponseDTO;
    private String coverLetter;
    private Instant responseTime = Instant.now();
}
