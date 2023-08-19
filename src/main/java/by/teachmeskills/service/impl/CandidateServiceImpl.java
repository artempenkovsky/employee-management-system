package by.teachmeskills.service.impl;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.UserRole;
import by.teachmeskills.repository.CandidateRepository;
import by.teachmeskills.service.CandidateService;
import by.teachmeskills.transformers.TransformCandidateRequestToCandidate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final TransformCandidateRequestToCandidate transformer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Candidate createCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = transformer.transform(candidateRequest);
        Set<UserRole> roles = candidate.getRoles();
        roles.add(UserRole.ROLE_CANDIDATE);
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId).orElse(null);
    }
}

