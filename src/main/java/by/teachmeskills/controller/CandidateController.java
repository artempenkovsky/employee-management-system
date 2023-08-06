package by.teachmeskills.controller;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.CandidateServiceImpl;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateServiceImpl candidateService;
    private final VacancyServiceImpl vacancyService;

    @PostMapping("/create")
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateRequest request) {
        Candidate candidate = candidateService.createCandidate(request);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }
    @GetMapping("/getVacancyByEmployer/{employerId}")
    public ResponseEntity<List<Vacancy>> getVacancyByEmployer(@PathVariable Long employerId) {
        List<Vacancy> vacancyList = vacancyService.getVacancyByEmployer(employerId);
        return new ResponseEntity<>(vacancyList, HttpStatus.OK);
    }
    @GetMapping("/getActiveVacancyByEmployer/{employerId}")
    public ResponseEntity<List<Vacancy>> getActiveVacancyByEmployer(@PathVariable Long employerId) {
        List<Vacancy> vacancyList = vacancyService.getActiveVacancyByEmployer(employerId);
        return new ResponseEntity<>(vacancyList, HttpStatus.OK);
    }
    @GetMapping("/getCandidateById/{candidateId}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long candidateId) {
        Candidate candidate = candidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
}
