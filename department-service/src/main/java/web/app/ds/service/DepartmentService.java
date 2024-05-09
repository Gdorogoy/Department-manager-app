package web.app.ds.service;

import web.app.ds.dto.DepartmentDto;
import web.app.ds.dto.WorkDepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getDepartments();
    DepartmentDto deleteDepartment(Long id);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartmentByCode(String code);

    WorkDepartmentDto getWorkDepartmentById(Long id);
    WorkDepartmentDto getWorkDepartmentByCode(String code);




}
