package web.es.ts.service;

import web.es.ts.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getProjectsByEmployeeId(Long employeeId);

    List<ProjectDto> createProject(List<ProjectDto> projectDtos);
    ProjectDto getProjectById(Long id);

    List<ProjectDto> getAllProjects();

    ProjectDto deleteProject(Long id);
    ProjectDto updateProject(ProjectDto projectDto);





}
