package by.teachmeskills.controller;

import by.teachmeskills.dto.VacancyRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy")
public class VacancyController {
    private final VacancyServiceImpl vacancyService;

    public VacancyController(VacancyServiceImpl vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public ResponseEntity<List<Vacancy>> showVacancy() {
        List<Vacancy> allVacancies = vacancyService.getAllVacancies();
        return new ResponseEntity<>(allVacancies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody VacancyRequest request) {
        Vacancy vacancy = vacancyService.createVacancy(request);
        return new ResponseEntity<>(vacancy, HttpStatus.CREATED);
    }

    @GetMapping("/vacancyPage")
    public ResponseEntity<Page<Vacancy>> showPageVacancy(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<Vacancy> allVacancies = vacancyService.getAllVacancy(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(allVacancies, HttpStatus.OK);
    }

    @GetMapping("/vacancyPageByEmployer")
    public ResponseEntity<Page<Vacancy>> showPageVacancyByEmployerId(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                     @RequestParam(value = "employerId") Long employerid) {
        Page<Vacancy> allVacancies = vacancyService.getAllVacancyByEmployer(
                employerid,
                PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(allVacancies, HttpStatus.OK);
    }
    @GetMapping("/vacancyPageByEmployerAndStatusIsActive")
    public ResponseEntity<Page<Vacancy>> findAllByEmployerIdAndStatusIsActive(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                     @RequestParam(value = "employerId") Long employerid) {
        Page<Vacancy> allVacancies = vacancyService.getAllByEmployerIdAndStatusIsActive(
                employerid,
                PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(allVacancies, HttpStatus.OK);
    }
    @GetMapping("/getEmployerByVacancyId/{vacancyId}")
    public ResponseEntity<Employer> getEmployerByVacancyId(@PathVariable Long vacancyId) {
        Employer employer = vacancyService.getEmployerByVacancyId(vacancyId);
        return new ResponseEntity<>(employer, HttpStatus.OK);
    }
}
