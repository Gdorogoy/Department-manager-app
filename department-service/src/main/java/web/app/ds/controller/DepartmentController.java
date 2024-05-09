package web.app.ds.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.ds.dto.DepartmentDto;
import web.app.ds.dto.WorkDepartmentDto;
import web.app.ds.service.impl.DepartmentServiceImpl;

import java.util.List;


@Tag(
        name = "Department service - Department controller",
        description = "The available exposes of Department service by Department controller"
)
@RestController
@RequestMapping("/api/depart")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(
            summary = "Get Department by Department Id",
            description = "Get Department By Id REST API is used to get the Department from database by its Id value."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get all Departments ",
            description = "Get All Departments REST API is used to get all of the existing Departments in the database. "
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get")
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }


    @Operation(
            summary = "Create Department",
            description = "Create Department REST API allows to create new department."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @PostMapping("/add")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto department){
        return new ResponseEntity<>(departmentService.saveDepartment(department),HttpStatus.OK);
    }

    @Operation(
            summary = "Update Department By Id",
            description = "Update Department By Id REST API used to update the Department using its id."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS UPDATED"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id ,@RequestBody DepartmentDto department){
        department.setId(id);
        return new ResponseEntity<>(departmentService.updateDepartment(department),HttpStatus.OK);
    }

    @Operation(
            summary = "Delete By Id",
            description = "Delete By Id REST API used to delete Department for database by using its id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS DELETED"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.deleteDepartment(id),HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department by Department Code ",
            description = "Get Department By Code REST API is used to get the Department from database by its Code value."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/getc/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String code){
        return new ResponseEntity<>(departmentService.findDepartmentByCode(code),HttpStatus.OK);
    }


    @Operation(
            summary = "Get Work Department by Department Code ",
            description = "Get Work Department By Code REST API is used to get the Department and all of its Employees from database by its Code value."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get_workd/code/{code}")
    public ResponseEntity<WorkDepartmentDto> getWorkDepartmentByCode(@PathVariable String code){
        return new ResponseEntity<>(departmentService.getWorkDepartmentByCode(code), HttpStatus.OK);
    }


    @Operation(
            summary = "Get Work Department by Department Id ",
            description = "Get Work Department By Code REST API is used to get the Department and all of its Employees from database by its Id value"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS FOUND"
    )
    @GetMapping("/get_workd/id/{id}")
    public ResponseEntity<WorkDepartmentDto> getWorkDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getWorkDepartmentById(id), HttpStatus.OK);
    }




}
