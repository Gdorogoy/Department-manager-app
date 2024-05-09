package web.app.es.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.app.es.dto.DepartmentDto;
import web.app.es.dto.WorkDepartmentDto;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClientDepartment {


    @GetMapping("/api/depart/getc/{code}")
    DepartmentDto getDepartment(@PathVariable String code);


    @GetMapping("/get_workd/code/{code}")
    WorkDepartmentDto getWorkDepartmentByCode(@PathVariable String code);


}










