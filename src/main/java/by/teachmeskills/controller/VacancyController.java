package by.teachmeskills.controller;

import by.teachmeskills.dto.EmployerResponse;
import by.teachmeskills.dto.VacancyRequest;
import by.teachmeskills.dto.VacancyResponseDTO;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.Vacancy;
import by.teachmeskills.service.impl.VacancyServiceImpl;
import by.teachmeskills.transformers.TransformEmployerToEmployerResponse;
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
@RequestMapping("/api/v1/vacancy")
public class VacancyController {
    private final VacancyServiceImpl vacancyService;
    private final TransformVacancyToVacancyResponse transformVacancyToVacancyResponse;
    private final TransformEmployerToEmployerResponse transformEmployerToEmployerResponse;

    public VacancyController(VacancyServiceImpl vacancyService, TransformVacancyToVacancyResponse transformVacancyToVacancyResponse, TransformEmployerToEmployerResponse transformEmployerToEmployerResponse) {
        this.vacancyService = vacancyService;
        this.transformVacancyToVacancyResponse = transformVacancyToVacancyResponse;
        this.transformEmployerToEmployerResponse = transformEmployerToEmployerResponse;
    }

    @PostMapping("/create")
    public ResponseEntity<VacancyResponseDTO> createVacancy(@RequestBody VacancyRequest request) {
        Vacancy vacancy = vacancyService.createVacancy(request);
        return new ResponseEntity<>(transformVacancyToVacancyResponse.transform(vacancy), HttpStatus.CREATED);
    }

    @GetMapping("/vacancyPage")
    public ResponseEntity<Page<VacancyResponseDTO>> showPageVacancy(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<Vacancy> allVacancies = vacancyService.getAllVacancy(PageRequest.of(pageNumber, pageSize));
        List<VacancyResponseDTO> collect = allVacancies.stream().map(vacancy -> transformVacancyToVacancyResponse.transform(vacancy)).collect(Collectors.toList());
        PageImpl<VacancyResponseDTO> vacancyResponseDTOS = new PageImpl<>(collect, PageRequest.of(pageNumber, pageSize), collect.size());
        return new ResponseEntity<>(vacancyResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/vacancyPageByEmployer")
    public ResponseEntity<Page<VacancyResponseDTO>> showPageVacancyByEmployerId(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                                @RequestParam(value = "employerId") Long employerId) {
        Page<Vacancy> allVacancies = vacancyService.getAllVacancyByEmployer(
                employerId,
                PageRequest.of(pageNumber, pageSize));
        List<VacancyResponseDTO> collect = allVacancies.stream().map(vacancy -> transformVacancyToVacancyResponse.transform(vacancy)).collect(Collectors.toList());
        PageImpl<VacancyResponseDTO> vacancyResponseDTOS = new PageImpl<>(collect, PageRequest.of(pageNumber, pageSize), collect.size());
        return new ResponseEntity<>(vacancyResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/vacancyPageByEmployerAndStatusIsActive")
    public ResponseEntity<Page<VacancyResponseDTO>> findAllByEmployerIdAndStatusIsActive(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                              @RequestParam(value = "employerId") Long employerid) {
        Page<Vacancy> allVacancies = vacancyService.getAllByEmployerIdAndStatusIsActive(
                employerid,
                PageRequest.of(pageNumber, pageSize));
        List<VacancyResponseDTO> collect = allVacancies.stream().map(vacancy -> transformVacancyToVacancyResponse.transform(vacancy)).collect(Collectors.toList());
        PageImpl<VacancyResponseDTO> vacancyResponseDTOS = new PageImpl<>(collect, PageRequest.of(pageNumber, pageSize), collect.size());
        return new ResponseEntity<>(vacancyResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/getEmployerByVacancyId/{vacancyId}")
    public ResponseEntity<EmployerResponse> getEmployerByVacancyId(@PathVariable Long vacancyId) {
        Employer employer = vacancyService.getEmployerByVacancyId(vacancyId);
        return new ResponseEntity<>(transformEmployerToEmployerResponse.transform(employer), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
