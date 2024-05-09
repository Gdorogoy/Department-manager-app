package web.app.es.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.es.dto.APIResponseDto;
import web.app.es.dto.EmployeeDto;
import web.app.es.dto.ProjectDto;
import web.app.es.dto.WorkDepartmentDto;
import web.app.es.service.Impl.EmployeeServiceImpl;

import java.util.List;


@Tag(
        name = "Employee service - Employee controller",
        description = "The available exposes of Employee service by Employee controller"
)
@RestController
@RequestMapping("/api/empl")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    @Operation(
            summary = "Delete By Id",
            description = "Delete By Id REST API used to delete Emoloyee for database by using its id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS DELETED"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Update Employee By Id",
            description = "Update Employee By Id REST API used to update the Employee using its id and only assigning it existing department."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS UPDATED"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
        employeeDto.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Add Employee",
            description = "Add Employee REST API allows to add new Employee for existing department only."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee by Employee Id",
            description = "Get Employee By Id REST API is used to get the Employee from database by its Id value."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Get all Employees ",
            description = "Get All Employees REST API is used to get all of the existing Employees in the database. "
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/gets")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        return new ResponseEntity<>(employeeService.getAll(),HttpStatus.OK);

    }

    @Operation(
            summary = "Get Employee ",
            description = "Get Employee REST API is used to get the Employee from database by its Id value or if not given the id then by the code."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get/")
    public ResponseEntity<APIResponseDto> getEmployee(@RequestParam(required = false) Long id,
                                                      @RequestParam(required = false) String email) {
        if (id != null) {
            return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
        } else if (email != null) {
            return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get Employee Of Department ",
            description = "Get Employee Of Department REST API is used to get the Employees of the Department."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get_empl/{code}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesOfDepartment(@PathVariable String code){
        return new ResponseEntity<>(employeeService.getAllEmployeesByCode(code),HttpStatus.OK);
    }
    @GetMapping("/get_empl_depart/{id}")
    public ResponseEntity<WorkDepartmentDto> getDepartmentOfEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getDepartmentOfEmployee(id),HttpStatus.OK);
    }

    @PutMapping("/fnsh_tsk/{id}")
    public ResponseEntity<ProjectDto> finishTask(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.updateTaskStatus(id),HttpStatus.OK);
    }


}
