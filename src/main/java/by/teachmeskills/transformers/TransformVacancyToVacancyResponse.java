package by.teachmeskills.transformers;

import by.teachmeskills.dto.VacancyResponseDTO;
import by.teachmeskills.model.Vacancy;
import org.springframework.stereotype.Component;

@Component
public class TransformVacancyToVacancyResponse {
    private final TransformEmployerToEmployerResponse transformEmployerToEmployerResponse;

    public TransformVacancyToVacancyResponse(TransformEmployerToEmployerResponse transformEmployerToEmployerResponse) {
        this.transformEmployerToEmployerResponse = transformEmployerToEmployerResponse;
    }

    public VacancyResponseDTO transform(Vacancy vacancy) {
        VacancyResponseDTO vacancyResponseDTO = new VacancyResponseDTO();
        vacancyResponseDTO.setId(vacancy.getId());
        vacancyResponseDTO.setDescription(vacancy.getDescription());
        vacancyResponseDTO.setTitle(vacancy.getTitle());
        vacancyResponseDTO.setActive(vacancy.isActive());
        vacancyResponseDTO.setEmployerResponse(transformEmployerToEmployerResponse.transform(vacancy.getEmployer()));
        return vacancyResponseDTO;
    }
}
