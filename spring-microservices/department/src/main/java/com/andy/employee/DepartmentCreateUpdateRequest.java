package com.andy.employee;

public record DepartmentCreateUpdateRequest(
        String departmentName,
        String departmentDescription,
        String departmentCode
) {
}
