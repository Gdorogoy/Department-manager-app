package web.app.es.service.Impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.es.dto.*;
import web.app.es.entity.Employee;
import web.app.es.exception.DepartmentNotFoundException;
import web.app.es.repository.EmployeeRepository;
import web.app.es.service.APIClientDepartment;
import web.app.es.service.APIClientProject;
import web.app.es.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final APIClientDepartment apiClientDepartment;
    private final APIClientProject apiClientProject;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, APIClientDepartment apiClient, APIClientProject apiClientProject) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.apiClientDepartment = apiClient;
        this.apiClientProject = apiClientProject;
    }

    @CircuitBreaker(name ="${spring.application.name", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployee(Long id) {
        APIResponseDto apiResponseDto=new APIResponseDto(
                modelMapper.map(employeeRepository.findById(id).get(),EmployeeDto.class),
                apiClientDepartment.getDepartment(employeeRepository.findById(id).get().getDepartmentCode()),
                apiClientProject.getProjectsOfEmployee(id)
        );
        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees=employeeRepository.findAll();
        return employees.stream().map((employee) -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        try {
            if (checkIfCodesValid(employeeDto.getDepartmentCode())) {
                employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
                return employeeDto;
            }
        } catch (DepartmentNotFoundException ex) {
            throw ex;
        }
        return null;
    }


    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        try {
            if (checkIfCodesValid(employeeDto.getDepartmentCode())) {
                Employee exisitingEmployee=employeeRepository.findById(employeeDto.getId()).get();
                exisitingEmployee.setEmail(employeeDto.getEmail());
                exisitingEmployee.setFirstName(employeeDto.getFirstName());
                exisitingEmployee.setLastName(employeeDto.getLastName());
                exisitingEmployee.setDepartmentCode(employeeDto.getDepartmentCode());
                employeeRepository.save(exisitingEmployee);
                return employeeDto;
            }
        } catch (DepartmentNotFoundException ex) {
            throw ex;
        }
        return null;
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {
        EmployeeDto dto=modelMapper.map(employeeRepository.findById(id).get(),EmployeeDto.class);
        employeeRepository.deleteById(id);
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByCode(String code) {
        List<Employee> employees=employeeRepository.findAllByDepartmentCode(code);
        return employees.stream().map((employee) -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name ="${spring.application.name", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeByEmail(String email) {
        APIResponseDto apiResponseDto=new APIResponseDto(
                modelMapper.map(employeeRepository.findByEmail(email),EmployeeDto.class),
                apiClientDepartment.getDepartment(employeeRepository.findByEmail(email).getDepartmentCode()),
                apiClientProject.getProjectsOfEmployee(employeeRepository.findByEmail(email).getId())
        );
        return apiResponseDto;
    }

    @Override
    @CircuitBreaker(name ="${spring.application.name", fallbackMethod = "getDefaultDepartment")
    public ProjectDto updateTaskStatus(Long id){
        ProjectDto projectDto= apiClientProject.getProjectById(id);
        projectDto.setIsDone(true);
        apiClientProject.updateProject(id,projectDto);
        return projectDto;
    }

    @Override
    public WorkDepartmentDto getDepartmentOfEmployee(Long id) {
        return apiClientDepartment.getWorkDepartmentByCode(employeeRepository.findById(id).get().getDepartmentCode());
    }

    public APIResponseDto getDefaultDepartment(Long id,Exception exception) {
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDepartmentName("Error Department");
        departmentDto.setDepartmentDescription("Department Service Down");
        departmentDto.setDepartmentCode("#ERORR");



        APIResponseDto apiResponseDto=new APIResponseDto(
                modelMapper.map(employeeRepository.findById(id).get(),EmployeeDto.class),
                departmentDto,
                null
        );
        return apiResponseDto;
    }


    public boolean checkIfCodesValid(String departmentCode) {
        boolean isDepartmentValid = apiClientDepartment.getDepartment(departmentCode) != null;
        if (!isDepartmentValid)
            throw new DepartmentNotFoundException();
        return true;
    }




}
