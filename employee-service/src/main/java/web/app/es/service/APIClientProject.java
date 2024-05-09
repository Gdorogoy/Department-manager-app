package web.app.es.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import web.app.es.dto.ProjectDto;

import java.util.List;

@FeignClient("TASK-SERVICE")
public interface APIClientProject {

    @GetMapping("api/task/get_tasks/{id}")
    List<ProjectDto> getProjectsOfEmployee(@PathVariable Long id);

    @PutMapping("/update_task/{id}")
    ProjectDto updateProject(@PathVariable Long id,ProjectDto projectDto);


    @GetMapping("/get_task/{id}")
    ProjectDto getProjectById(@PathVariable Long id);
}
