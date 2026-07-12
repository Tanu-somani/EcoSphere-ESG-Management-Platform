package com.odoo.backendegs.service.department;

import com.odoo.backendegs.dto.request.CreateDepartmentRequest;
import com.odoo.backendegs.dto.response.DepartmentResponse;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepo departmentRepo,
                             ModelMapper modelMapper) {
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
    }

    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {

        Department department = modelMapper.map(request, Department.class);

        Department savedDepartment = departmentRepo.save(department);

        return getResponse(savedDepartment);
    }

    public DepartmentResponse getDepartment(Long id) {

        Department department = departmentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department " + id + " not found"));

        return getResponse(department);
    }

    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepo.findAll()
                .stream()
                .map(this::getResponse)
                .toList();
    }

    private DepartmentResponse getResponse(Department department) {

        return modelMapper.map(department, DepartmentResponse.class);
    }

}