package com.odoo.backendegs.service.environment;

import com.odoo.backendegs.dto.request.CreateResourceRequest;
import com.odoo.backendegs.dto.response.ResourceResponse;
import com.odoo.backendegs.entity.environmental.EmissionFactor;
import com.odoo.backendegs.entity.environmental.Resource;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.environmental.ResourceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepo resourceRepo;
    private final ModelMapper modelMapper;

    public ResourceService(ResourceRepo resourceRepo,
                           ModelMapper modelMapper) {

        this.resourceRepo = resourceRepo;
        this.modelMapper = modelMapper;
    }

    public ResourceResponse createResource(CreateResourceRequest request){

        Resource resource = modelMapper.map(request, Resource.class);

        EmissionFactor emissionFactor = new EmissionFactor();
        emissionFactor.setFactor(request.getEmissionFactor());
        emissionFactor.setResource(resource);

        resource.setEmissionFactor(emissionFactor);

        Resource saved = resourceRepo.save(resource);

        return getResponse(saved);

    }

    public ResourceResponse getResource(Long id){

        Resource resource = resourceRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource " + id + " not found"));

        return getResponse(resource);

    }

    public List<ResourceResponse> getAllResources(){

        return resourceRepo.findAll()
                .stream()
                .map(this::getResponse)
                .toList();

    }

    private ResourceResponse getResponse(Resource resource){

        ResourceResponse response =
                modelMapper.map(resource, ResourceResponse.class);

        response.setEmissionFactor(
                resource.getEmissionFactor().getFactor()
        );

        return response;

    }

}