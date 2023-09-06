package com.andy.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public static final String DEPARTMENT_SERVICE_NAME = "DEPARTMENT-SERVICE";
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final WebClient webClient;
    private final DepartmentClient departmentClient;
    private final EurekaDiscoveryClient discoveryClient;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeDTOMapper employeeDTOMapper,
                           WebClient webClient,
                           DepartmentClient departmentClient,
                           EurekaDiscoveryClient discoveryClient) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
        this.webClient = webClient;
        this.departmentClient = departmentClient;
        this.discoveryClient = discoveryClient;
    }

    public void createEmployee(EmployeeCreateUpdateRequest request) {
        Employee employee = new Employee(
                request.firstName(),
                request.lastName(),
                request.email()
        );

        employeeRepository.save(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> new EmployeeResponseDTO(
                        employeeDTOMapper.apply(employee),
                        departmentClient.getDepartment(employee.getDepartmentCode())
                ))
                .collect(Collectors.toList());

    }

    public EmployeeResponseDTO getEmployeeById(Long id) {

        EmployeeDTO employeeDTO = employeeRepository
                .findById(id)
                .map(employeeDTOMapper)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));

        List<ServiceInstance> instances = discoveryClient.getInstances(DEPARTMENT_SERVICE_NAME);

        if (instances.isEmpty()) {
            throw new RuntimeException("Department service not available");
        }

        String departmentServiceUri = instances.get(0).getUri().toString();
        DepartmentDTO departmentDTO = webClient.get()
                .uri(departmentServiceUri + "/api/departments/department/" + employeeDTO.departmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

        return new EmployeeResponseDTO(employeeDTO, departmentDTO);

    }

    public void updateEmployee(Long id, EmployeeCreateUpdateRequest request) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employee not found with id: " + id);
        }
    }
}
