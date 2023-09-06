package com.andy.employee;


public record DepartmentDTO(
        Long id,
        String departmentName,
        String departmentDescription,
        String departmentCode) {
}
