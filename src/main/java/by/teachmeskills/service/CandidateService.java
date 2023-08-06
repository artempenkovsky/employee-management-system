package by.teachmeskills.service;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.model.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateRequest candidateRequest);

    Candidate getCandidateById(Long candidateId);
}
