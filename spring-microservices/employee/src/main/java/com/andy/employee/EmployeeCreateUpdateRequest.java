package com.andy.employee;

public record EmployeeCreateUpdateRequest(
        String firstName,
        String lastName,
        String email
) {
}
