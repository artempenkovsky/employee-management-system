package by.teachmeskills.controller;

import by.teachmeskills.dto.OpportunityResponse;
import by.teachmeskills.dto.VacancyResponseRequest;
import by.teachmeskills.model.Opportunity;
import by.teachmeskills.service.impl.VacancyResponseServiceImpl;
import by.teachmeskills.transformers.TransformOpportunityToOpportunityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vacancy_response")
public class VacancyResponseController {
    private final VacancyResponseServiceImpl vacancyResponseService;
    private final TransformOpportunityToOpportunityResponse transformOpportunityToOpportunityResponse;

    public VacancyResponseController(VacancyResponseServiceImpl vacancyResponseService, TransformOpportunityToOpportunityResponse transformOpportunityToOpportunityResponse) {
        this.vacancyResponseService = vacancyResponseService;
        this.transformOpportunityToOpportunityResponse = transformOpportunityToOpportunityResponse;
    }

    @PostMapping("/create")
    public ResponseEntity<OpportunityResponse> createVacancyResponse(@RequestBody VacancyResponseRequest request) {
        Opportunity vacancyOpportunity = vacancyResponseService.createVacancyResponse(request);
        return new ResponseEntity<>(transformOpportunityToOpportunityResponse.transform(vacancyOpportunity), HttpStatus.CREATED);
    }
}
