package web.app.ds.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.ds.dto.DepartmentDto;
import web.app.ds.dto.WorkDepartmentDto;
import web.app.ds.entity.Department;
import web.app.ds.exception.ResourceNotFoundException;
import web.app.ds.repository.DepartmentRepository;
import web.app.ds.service.APIClientEmployee;
import web.app.ds.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final APIClientEmployee apiClientEmployee;



    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, APIClientEmployee apiClientEmployee) {
        this.departmentRepository = departmentRepository;
        this.apiClientEmployee = apiClientEmployee;
        this.modelMapper=new ModelMapper();
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        departmentRepository.save(modelMapper.map(departmentDto, Department.class));
        return departmentDto;
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        try {
            Department department=(departmentRepository.findById(id)).get();
            return modelMapper.map(department,DepartmentDto.class);
        }
        catch (ResourceNotFoundException ex){
            throw ex;
        }
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public DepartmentDto deleteDepartment(Long id) {
        try {
            if(departmentRepository.findById(id).isPresent()){
                Department departmentToDelete=(departmentRepository.findById(id)).get();
                departmentRepository.deleteById(id);
                return modelMapper.map(departmentToDelete,DepartmentDto.class);
            }
        }catch (ResourceNotFoundException ex){
            throw ex;
        }
        return null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department existingDepartment=departmentRepository.findById(departmentDto.getId()).get();
        existingDepartment.setDepartmentCode(departmentDto.getDepartmentCode());
        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());
        departmentRepository.save(existingDepartment);
        return modelMapper.map(existingDepartment,DepartmentDto.class);
    }

    @Override
    public DepartmentDto findDepartmentByCode(String code) {
        try {
            Department department = departmentRepository.findByDepartmentCode(code);
            return modelMapper.map(department, DepartmentDto.class);
        } catch (ResourceNotFoundException ex){
            throw ex;
        }

    }

    @Override
    public WorkDepartmentDto getWorkDepartmentById(Long id) {
        try {
            if(departmentRepository.findById(id).isPresent()){
                return new WorkDepartmentDto(modelMapper.map(departmentRepository.findById(id).get(),DepartmentDto.class),
                        apiClientEmployee.getEmployeesOfDepartment(
                                departmentRepository.findById(id).get().getDepartmentCode()
                        ));
            }
        }catch (ResourceNotFoundException ex) {
            throw ex;
        }
        return null;
    }

    @Override
    public WorkDepartmentDto getWorkDepartmentByCode(String code) {
        try {
            if(departmentRepository.findById(departmentRepository.findByDepartmentCode(code).getId()).isPresent()){
                return new WorkDepartmentDto(modelMapper.map(departmentRepository.findByDepartmentCode(code),DepartmentDto.class),
                        apiClientEmployee.getEmployeesOfDepartment(
                                code
                        ));
            }
        }catch (ResourceNotFoundException ex) {
            throw ex;
        }
        return null;
    }



}