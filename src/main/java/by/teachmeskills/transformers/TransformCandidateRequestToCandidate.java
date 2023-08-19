package by.teachmeskills.transformers;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class TransformCandidateRequestToCandidate {
    public Candidate transform(CandidateRequest candidateRequest){
        Candidate candidate = new Candidate();
        candidate.setEmail(candidateRequest.getEmail());
        candidate.setResume(candidateRequest.getResume());
        candidate.setLastName(candidateRequest.getLastName());
        candidate.setFirstName(candidateRequest.getFirstName());
        candidate.setUsername(candidateRequest.getUsername());
        candidate.setPassword(candidateRequest.getPassword());
        return candidate;
    }
}
