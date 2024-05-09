package web.app.es.service;

import org.aspectj.weaver.World;
import web.app.es.dto.APIResponseDto;
import web.app.es.dto.EmployeeDto;
import web.app.es.dto.ProjectDto;
import web.app.es.dto.WorkDepartmentDto;

import java.util.List;

public interface EmployeeService {
    APIResponseDto getEmployee(Long id);
    List<EmployeeDto> getAll();
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    EmployeeDto deleteEmployee(Long id);
    List<EmployeeDto> getAllEmployeesByCode(String code);
    APIResponseDto getEmployeeByEmail(String email);

    ProjectDto updateTaskStatus(Long id);

    WorkDepartmentDto getDepartmentOfEmployee(Long id);


}
