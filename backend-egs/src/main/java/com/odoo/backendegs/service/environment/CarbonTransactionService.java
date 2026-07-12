package com.odoo.backendegs.service.environment;

import com.odoo.backendegs.dto.request.CreateCarbonTransactionRequest;
import com.odoo.backendegs.dto.response.CarbonTransactionResponse;
import com.odoo.backendegs.engine.CarbonCalculationEngine;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.entity.environmental.CarbonTransaction;
import com.odoo.backendegs.entity.environmental.EmissionFactor;
import com.odoo.backendegs.entity.environmental.Resource;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import com.odoo.backendegs.repo.environmental.CarbonTransactionRepository;
import com.odoo.backendegs.repo.environmental.ResourceRepo;
import com.odoo.backendegs.service.esg.EsgScoreService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarbonTransactionService {

    private final CarbonCalculationEngine carbonCalculationEngine;
    private final CarbonTransactionRepository carbonTransactionRepository;
    private final ResourceRepo resourceRepo;
    private final DepartmentRepo departmentRepo;
    private final ModelMapper modelMapper;
    private final EsgScoreService esgScoreService;

    public CarbonTransactionService(CarbonCalculationEngine carbonCalculationEngine, CarbonTransactionRepository carbonTransactionRepository, ResourceRepo resourceRepo, DepartmentRepo departmentRepo, ModelMapper modelMapper, EsgScoreService esgScoreService) {
        this.carbonCalculationEngine = carbonCalculationEngine;
        this.carbonTransactionRepository = carbonTransactionRepository;
        this.resourceRepo = resourceRepo;
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
        this.esgScoreService = esgScoreService;
    }


    @Transactional
    public CarbonTransactionResponse createCarbonTransaction(CreateCarbonTransactionRequest request){

        Long departmentId = request.getDepartmentId();

        Department department = getDepartment(departmentId);
        Resource resource = getResource(request.getResourceId());

        EmissionFactor emissionFactor = resource.getEmissionFactor();

        double calculatedEmission = carbonCalculationEngine
                .calculateCarbonEmission(request.getQuantity(), emissionFactor.getFactor());

        CarbonTransaction mapped = modelMapper.map(request, CarbonTransaction.class);
        mapped.setDepartment(department);
        mapped.setResource(resource);
        mapped.setEmissionFactorUsed(emissionFactor.getFactor());
        mapped.setCarbonGenerated(calculatedEmission);

        CarbonTransaction saved = carbonTransactionRepository.save(mapped);


        esgScoreService.updateDepartmentScore(departmentId);

        return getResponse(saved);


    }

    private  CarbonTransactionResponse getResponse(CarbonTransaction saved){

        CarbonTransactionResponse response = new CarbonTransactionResponse();

        response.setId(saved.getId());
        response.setDepartmentId(saved.getDepartment().getId());
        response.setDepartmentName(saved.getDepartment().getName());
        response.setResourceId(saved.getResource().getId());
        response.setResourceName(saved.getResource().getName());
        response.setEmissionFactorUsed(saved.getEmissionFactorUsed());
        response.setCarbonGenerated(saved.getCarbonGenerated());
        response.setTransactionDate(saved.getTransactionDate());
        response.setQuantity(saved.getQuantity());

        return response;
    }

    public CarbonTransactionResponse getCarbonTransaction(Long id){

        CarbonTransaction transaction = carbonTransactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Carbon Transaction " + id + " not found"));

        return getResponse(transaction);
    }

    public List<CarbonTransactionResponse> getAllCarbonTransactions(){

        return carbonTransactionRepository.findAll()
                .stream()
                .map(this::getResponse)
                .toList();

    }

    public List<CarbonTransactionResponse> getDepartmentTransactions(Long departmentId){

        return carbonTransactionRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::getResponse)
                .toList();

    }

    public Double getDepartmentTotalCarbon(Long departmentId){

        return carbonTransactionRepository.getTotalCarbonEmission(departmentId);

    }

    private Department getDepartment(Long departmentId){

        return departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department "+ departmentId + " not found"));

    }

    private Resource getResource(Long resourceId){
        return resourceRepo.findById(resourceId).orElseThrow(() -> new ResourceNotFoundException("Resource "+ resourceId + " not found"));

    }
}
