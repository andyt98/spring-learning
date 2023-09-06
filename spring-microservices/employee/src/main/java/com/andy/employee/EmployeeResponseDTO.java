package com.andy.employee;

public record EmployeeResponseDTO(
        EmployeeDTO employee,
        DepartmentDTO department
) {
}
