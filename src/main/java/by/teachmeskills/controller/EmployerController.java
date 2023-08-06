package by.teachmeskills.controller;

import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Response;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.EmployerServiceImpl;
import by.teachmeskills.service.impl.ResponseServiceImpl;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employers")
public class EmployerController {
    private final ResponseServiceImpl responseService;
    private final EmployerServiceImpl employerService;
    private final VacancyServiceImpl vacancyService;

    public EmployerController(ResponseServiceImpl responseService, EmployerServiceImpl employerService, VacancyServiceImpl vacancyService) {
        this.responseService = responseService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }


    @PostMapping("/create")
    public ResponseEntity<Employer> createEmployer(@RequestBody EmployerRequest request) {
        Employer employer = employerService.createEmployer(request);
        return new ResponseEntity<>(employer, HttpStatus.CREATED);
    }

    @GetMapping("/getResponseByVacancy/{vacancyId}")
    public ResponseEntity<List<Response>> getResponseByVacancy(@PathVariable Long vacancyId) {
        List<Response> responseByVacancy = responseService.getResponseByVacancy(vacancyId);
        return new ResponseEntity<>(responseByVacancy, HttpStatus.OK);
    }

    @GetMapping("/getResponseByEmployer/{employerId}")
    public ResponseEntity<List<Response>> getResponseByEmployer(@PathVariable Long employerId) {
        List<Response> responseByEmployer = responseService.getResponseByEmployer(employerId);
        return new ResponseEntity<>(responseByEmployer, HttpStatus.OK);
    }

    @PostMapping("/changeVacancyStatus/{vacancyId}")
    public ResponseEntity<Vacancy> changeVacancyStatus(@PathVariable Long vacancyId) {
        Vacancy vacancy = vacancyService.changeVacancyStatus(vacancyId);
        return new ResponseEntity<>(vacancy, HttpStatus.CREATED);
    }

    @GetMapping("/getResponseByEmployerWithPage")
    public ResponseEntity<Page<Response>> getResponseByEmployerWithPage(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                        @RequestParam(value = "employerId") Long employerId){
        Page<Response> responseByEmployer = responseService.getResponseByEmployerWithPage(employerId, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseByEmployer, HttpStatus.OK);
    }
}

