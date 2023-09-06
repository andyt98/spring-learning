package com.andy.employee;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DepartmentDTOMapper implements Function<Department, DepartmentDTO> {

    @Override
    public DepartmentDTO apply(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }
}
