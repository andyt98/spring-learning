package com.andy.employee;


public record EmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String departmentCode
) {
}
