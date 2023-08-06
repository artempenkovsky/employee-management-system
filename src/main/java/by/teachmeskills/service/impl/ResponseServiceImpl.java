package by.teachmeskills.service.impl;

import by.teachmeskills.dto.ResponseRequest;
import by.teachmeskills.model.Response;
import by.teachmeskills.repository.ResponseRepository;
import by.teachmeskills.service.ResponseService;
import by.teachmeskills.transformers.TransformResponseRequestToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ResponseServiceImpl implements ResponseService {
    private final TransformResponseRequestToResponse transformer;
    private final ResponseRepository responseRepository;

    public ResponseServiceImpl(TransformResponseRequestToResponse transformer,
                               ResponseRepository responseRepository) {
        this.transformer = transformer;
        this.responseRepository = responseRepository;
    }

    @Override
    public Response createResponse(ResponseRequest request) {
        Response response = transformer.transform(request);
        Response save = responseRepository.save(response);
        return save;
    }

    @Override
    public List<Response> getResponseByVacancy(Long vacancyId) {
        List<Response> byVacancyId = responseRepository.findByVacancyId(vacancyId);
        return byVacancyId;
    }

    @Override
    public List<Response> getResponseByEmployer(Long employerId) {
        List<Response> responseList = responseRepository.findAll();
        List<Response> result = new ArrayList<>();
        for (Response response : responseList) {
            if (response.getVacancy().getEmployer().getId().equals(employerId)) {
                result.add(response);
            }
        }
        return result;
    }

    @Override
    public Page<Response> getResponseByEmployerWithPage(Long employerId, Pageable pageable) {
        List<Response> responseList = getResponseByEmployer(employerId);
        Page<Response> responsePage = new PageImpl<Response>(responseList, pageable, responseList.size());
        return responsePage;
    }
}
