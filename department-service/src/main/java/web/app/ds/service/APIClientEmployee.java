package web.app.ds.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.app.ds.dto.EmployeeDto;

import java.util.List;

@FeignClient(name="EMPLOYEE-SERVICE")
public interface APIClientEmployee {

    @GetMapping("/api/empl/get_empl/{code}")
    List<EmployeeDto> getEmployeesOfDepartment(@PathVariable String code);

}
