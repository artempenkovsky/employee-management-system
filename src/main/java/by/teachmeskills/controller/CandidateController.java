package by.teachmeskills.controller;

import by.teachmeskills.dto.CandidateRequest;
import by.teachmeskills.dto.CandidateResponse;
import by.teachmeskills.dto.VacancyResponseDTO;
import by.teachmeskills.model.Candidate;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.CandidateServiceImpl;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import by.teachmeskills.transformers.TransformCandidateToCandidateResponse;
import by.teachmeskills.transformers.TransformVacancyToVacancyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateServiceImpl candidateService;
    private final VacancyServiceImpl vacancyService;
    private final TransformCandidateToCandidateResponse transformCandidateToCandidateResponse;
    private final TransformVacancyToVacancyResponse transformVacancyToVacancyResponse;

    @PostMapping("/create")
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest request) {
        Candidate candidate = candidateService.createCandidate(request);
        return new ResponseEntity<>(transformCandidateToCandidateResponse.transform(candidate), HttpStatus.CREATED);
    }
    @GetMapping("/getVacancyByEmployer/{employerId}")
    public ResponseEntity<List<VacancyResponseDTO>> getVacancyByEmployer(@PathVariable Long employerId) {
        List<Vacancy> vacancyList = vacancyService.getVacancyByEmployer(employerId);
        List<VacancyResponseDTO> collect = vacancyList.stream().map(vacancy -> transformVacancyToVacancyResponse.transform(vacancy)).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
    @GetMapping("/getActiveVacancyByEmployer/{employerId}")
    public ResponseEntity<List<VacancyResponseDTO>> getActiveVacancyByEmployer(@PathVariable Long employerId) {
        List<Vacancy> vacancyList = vacancyService.getActiveVacancyByEmployer(employerId);
        List<VacancyResponseDTO> collect = vacancyList.stream().map(vacancy -> transformVacancyToVacancyResponse.transform(vacancy)).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
    @GetMapping("/getCandidateById/{candidateId}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long candidateId) {
        Candidate candidate = candidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(transformCandidateToCandidateResponse.transform(candidate), HttpStatus.OK);
    }
}
