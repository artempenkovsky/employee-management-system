package by.teachmeskills.dto;

import lombok.Data;

@Data
public class VacancyResponseDTO {

    private Long id;

    private String title;

    private String description;

    private boolean active;
    private EmployerResponse employerResponse;
}
