package by.teachmeskills.controller;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.dto.CandidateResponse;
import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.dto.EmployerResponse;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.Employer;
import by.teachmeskills.service.impl.CandidateServiceImpl;
import by.teachmeskills.service.impl.EmployerServiceImpl;
import by.teachmeskills.transformers.TransformCandidateToCandidateResponse;
import by.teachmeskills.transformers.TransformEmployerToEmployerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {
    private final EmployerServiceImpl employerService;
    private final CandidateServiceImpl candidateService;
    private final TransformEmployerToEmployerResponse transformEmployerToEmployerResponse;
    private final TransformCandidateToCandidateResponse transformCandidateToCandidateResponse;
    @PostMapping("/createEmployer")
    public ResponseEntity<EmployerResponse> createEmployer(@RequestBody EmployerRequest request) {
        Employer employer = employerService.createEmployer(request);
        EmployerResponse employerResponse = transformEmployerToEmployerResponse.transform(employer);
        return new ResponseEntity<>(employerResponse, HttpStatus.CREATED);
    }
    @PostMapping("/createCandidate")
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest request) {
        Candidate candidate = candidateService.createCandidate(request);
        CandidateResponse candidateResponse = transformCandidateToCandidateResponse.transform(candidate);
        return new ResponseEntity<>(candidateResponse, HttpStatus.CREATED);
    }

}
