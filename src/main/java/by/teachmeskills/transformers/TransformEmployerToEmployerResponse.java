package by.teachmeskills.transformers;

import by.teachmeskills.dto.EmployerResponse;
import by.teachmeskills.model.Employer;
import org.springframework.stereotype.Component;

@Component
public class TransformEmployerToEmployerResponse {
    public EmployerResponse transform(Employer employer) {
        EmployerResponse employerResponse = new EmployerResponse();
        employerResponse.setId(employer.getId());
        employerResponse.setUsername(employer.getUsername());
        employerResponse.setWebsite(employer.getWebsite());
        employerResponse.setRoles(employer.getRoles());
        employerResponse.setName(employer.getName());
        employerResponse.setEmail(employer.getEmail());
        return employerResponse;
    }
}
