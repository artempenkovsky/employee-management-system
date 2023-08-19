package by.teachmeskills.controller;

import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.dto.EmployerResponse;
import by.teachmeskills.dto.OpportunityResponse;
import by.teachmeskills.dto.VacancyResponseDTO;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Opportunity;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.EmployerServiceImpl;
import by.teachmeskills.service.impl.VacancyResponseServiceImpl;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import by.teachmeskills.transformers.TransformEmployerToEmployerResponse;
import by.teachmeskills.transformers.TransformOpportunityToOpportunityResponse;
import by.teachmeskills.transformers.TransformVacancyToVacancyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employers")
public class EmployerController {
    private final VacancyResponseServiceImpl responseService;
    private final EmployerServiceImpl employerService;
    private final VacancyServiceImpl vacancyService;
    private final TransformEmployerToEmployerResponse transformEmployerToEmployerResponse;
    private final TransformOpportunityToOpportunityResponse transformOpportunityToOpportunityResponse;
    private final TransformVacancyToVacancyResponse transformVacancyToVacancyResponse;

    public EmployerController(VacancyResponseServiceImpl responseService, EmployerServiceImpl employerService, VacancyServiceImpl vacancyService, TransformEmployerToEmployerResponse transformEmployerToEmployerResponse, TransformOpportunityToOpportunityResponse transformOpportunityToOpportunityResponse, TransformVacancyToVacancyResponse transformVacancyToVacancyResponse) {
        this.responseService = responseService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
        this.transformEmployerToEmployerResponse = transformEmployerToEmployerResponse;
        this.transformOpportunityToOpportunityResponse = transformOpportunityToOpportunityResponse;
        this.transformVacancyToVacancyResponse = transformVacancyToVacancyResponse;
    }


    @PostMapping("/create")
    public ResponseEntity<EmployerResponse> createEmployer(@RequestBody EmployerRequest request) {
        Employer employer = employerService.createEmployer(request);
        return new ResponseEntity<>(transformEmployerToEmployerResponse.transform(employer), HttpStatus.CREATED);
    }

    @GetMapping("/getResponseByVacancy/{vacancyId}")
    public ResponseEntity<List<OpportunityResponse>> getResponseByVacancy(@PathVariable Long vacancyId) {
        List<Opportunity> vacancyOpportunityByVacancy = responseService.getResponseByVacancy(vacancyId);
        List<OpportunityResponse> collect = vacancyOpportunityByVacancy.stream().map(opportunity -> transformOpportunityToOpportunityResponse.transform(opportunity)).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @GetMapping("/getResponseByEmployer/{employerId}")
    public ResponseEntity<List<OpportunityResponse>> getResponseByEmployer(@PathVariable Long employerId) {
        List<Opportunity> vacancyOpportunityByEmployer = responseService.getVacancyResponseByEmployer(employerId);
        List<OpportunityResponse> collect = vacancyOpportunityByEmployer.stream().map(opportunity -> transformOpportunityToOpportunityResponse.transform(opportunity)).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @PostMapping("/setVacancyStatusTrue/{vacancyId}")
    public ResponseEntity<VacancyResponseDTO> setVacancyStatusTrue(@PathVariable Long vacancyId) {
        Vacancy vacancy = vacancyService.setVacancyStatusTrue(vacancyId);
        return new ResponseEntity<>(transformVacancyToVacancyResponse.transform(vacancy), HttpStatus.CREATED);
    }
    @PostMapping("/setVacancyStatusFalse/{vacancyId}")
    public ResponseEntity<VacancyResponseDTO> setVacancyStatusFalse(@PathVariable Long vacancyId) {
        Vacancy vacancy = vacancyService.setVacancyStatusFalse(vacancyId);
        return new ResponseEntity<>(transformVacancyToVacancyResponse.transform(vacancy), HttpStatus.CREATED);
    }

    @GetMapping("/getResponseByEmployerWithPage")
    public ResponseEntity<?> getResponseByEmployerWithPage(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                           @RequestParam(value = "employerId") Long employerId) {
        Page<Opportunity> responseByEmployer = responseService.getVacancyResponseByEmployerWithPage(employerId, PageRequest.of(pageNumber, pageSize));
        List<OpportunityResponse> collect = responseByEmployer.stream().map(opportunity -> transformOpportunityToOpportunityResponse.transform(opportunity)).collect(Collectors.toList());
        PageImpl<OpportunityResponse> opportunityResponses = new PageImpl<>(collect, PageRequest.of(pageNumber, pageSize), collect.size());
        return new ResponseEntity<>(opportunityResponses, HttpStatus.OK);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerException(IllegalArgumentException e){
        return e.getMessage();
    }
}

