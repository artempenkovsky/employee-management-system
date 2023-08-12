package by.teachmeskills.transformers;

import by.teachmeskills.dto.CandidateResponse;
import by.teachmeskills.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class TransformCandidateToCandidateResponse {
    public CandidateResponse transform(Candidate candidate) {
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateResponse.setId(candidate.getId());
        candidateResponse.setUsername(candidate.getUsername());
        candidateResponse.setRoles(candidate.getRoles());
        candidateResponse.setEmail(candidate.getEmail());
        candidateResponse.setResume(candidate.getResume());
        candidateResponse.setFirstName(candidate.getFirstName());
        candidateResponse.setLastName(candidate.getLastName());
        return candidateResponse;
    }
}
