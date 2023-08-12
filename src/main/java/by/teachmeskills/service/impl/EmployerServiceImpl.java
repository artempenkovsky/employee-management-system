package by.teachmeskills.service.impl;

import by.teachmeskills.dto.EmployerRequest;
import by.teachmeskills.model.Employer;
import by.teachmeskills.model.UserRole;
import by.teachmeskills.repository.EmployerRepository;
import by.teachmeskills.service.EmployerService;
import by.teachmeskills.transformers.TransformEmployerRequestToEmployer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final TransformEmployerRequestToEmployer transformer;
    private final PasswordEncoder passwordEncoder;

    public EmployerServiceImpl(EmployerRepository employerRepository, TransformEmployerRequestToEmployer transformer, PasswordEncoder passwordEncoder) {
        this.employerRepository = employerRepository;
        this.transformer = transformer;
        this.passwordEncoder = passwordEncoder;
    }

    public Employer createEmployer(EmployerRequest employerRequest) {
        Employer transform = transformer.transform(employerRequest);
        Set<UserRole> roles = transform.getRoles();
        roles.add(UserRole.ROLE_EMPLOYER);
        transform.setPassword(passwordEncoder.encode(transform.getPassword()));
        return employerRepository.save(transform);
    }
}
