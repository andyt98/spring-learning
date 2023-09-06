package com.andy.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentDTOMapper departmentDTOMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentDTOMapper departmentDTOMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentDTOMapper = departmentDTOMapper;
    }

    public void createDepartment(DepartmentCreateUpdateRequest request) {
        Department department = new Department(
                request.departmentName(),
                request.departmentDescription(),
                request.departmentCode()
        );

        departmentRepository.save(department);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentDTOMapper)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository
                .findById(id)
                .map(departmentDTOMapper)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
    }

    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        return departmentRepository
                .findDepartmentByDepartmentCode(departmentCode)
                .map(departmentDTOMapper)
                .orElseThrow(() -> new NotFoundException("Department not found with code: " + departmentCode));
    }

    public void updateDepartment(Long id, DepartmentCreateUpdateRequest request) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));

        department.setDepartmentName(request.departmentName());
        department.setDepartmentDescription(request.departmentDescription());
        department.setDepartmentCode(request.departmentCode());
        departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Department not found with id: " + id);
        }
    }
}
