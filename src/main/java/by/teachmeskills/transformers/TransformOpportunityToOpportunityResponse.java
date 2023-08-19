package by.teachmeskills.transformers;

import by.teachmeskills.dto.OpportunityResponse;
import by.teachmeskills.model.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class TransformOpportunityToOpportunityResponse {
    private final TransformCandidateToCandidateResponse transformCandidateToCandidateResponse;
    private final TransformVacancyToVacancyResponse transformVacancyToVacancyResponse;

    public TransformOpportunityToOpportunityResponse(TransformCandidateToCandidateResponse transformCandidateToCandidateResponse, TransformVacancyToVacancyResponse transformVacancyToVacancyResponse) {
        this.transformCandidateToCandidateResponse = transformCandidateToCandidateResponse;
        this.transformVacancyToVacancyResponse = transformVacancyToVacancyResponse;
    }

    public OpportunityResponse transform(Opportunity opportunity) {
        OpportunityResponse opportunityResponse = new OpportunityResponse();
        opportunityResponse.setResponseTime(opportunity.getResponseTime());
        opportunityResponse.setCandidateResponse(transformCandidateToCandidateResponse.transform(opportunity.getCandidate()));
        opportunityResponse.setVacancyResponseDTO(transformVacancyToVacancyResponse.transform(opportunity.getVacancy()));
        opportunityResponse.setId(opportunity.getId());
        opportunityResponse.setCoverLetter(opportunity.getCoverLetter());
        return opportunityResponse;
    }
}
