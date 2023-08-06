package by.teachmeskills.service.impl;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.repository.CandidateRepository;
import by.teachmeskills.service.CandidateService;
import by.teachmeskills.transformers.TransformCandidateRequestToCandidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final TransformCandidateRequestToCandidate transformer;

    @Override
    public Candidate createCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = transformer.transform(candidateRequest);
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId).orElse(null);
    }
}

