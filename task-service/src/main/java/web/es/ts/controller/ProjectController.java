package web.es.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.es.ts.dto.ProjectDto;
import web.es.ts.service.impl.ProjectServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get_tasks/{id}")
    public ResponseEntity<List<ProjectDto>> getProjectsOfEmployee(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectsByEmployeeId(id), HttpStatus.OK);
    }

    @PostMapping("/add_tasks")
    public ResponseEntity<List<ProjectDto>> createProjects(@RequestBody List<ProjectDto> list){
        return new ResponseEntity<>(projectService.createProject(list),HttpStatus.OK);
    }

    @GetMapping("/get_task/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectById(id),HttpStatus.OK);
    }

    @GetMapping("/delete_task/{id}")
    public ResponseEntity<ProjectDto> deleteProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.deleteProject(id),HttpStatus.OK);
    }

    @PutMapping("/update_task/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id,ProjectDto projectDto){
        projectDto.setId(id);
        return new ResponseEntity<>(projectService.updateProject(projectDto),HttpStatus.OK);
    }
}
