package web.es.ts.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.es.ts.dto.ProjectDto;
import web.es.ts.enity.Project;
import web.es.ts.repository.ProjectRepository;
import web.es.ts.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProjectDto> getProjectsByEmployeeId(Long employeeId) {
        List<Project> projects=projectRepository.findByEmployeeId(employeeId);
        return projects.stream().map((project) -> modelMapper.map(project,ProjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> createProject(List<ProjectDto> projectDtos) {

        List<ProjectDto> projects=projectDtos;
        List<Project> listOfProjects=projects.stream().map((project) -> modelMapper.map(project,Project.class))
                .collect(Collectors.toList());
        for (Project i:listOfProjects){
            projectRepository.save(i);
        }
        return projectDtos;
    }


    @Override
    public ProjectDto getProjectById(Long id) {
        return modelMapper.map(projectRepository.findById(id).get(),ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects=projectRepository.findAll();
        return projects.stream().map((project) -> modelMapper.map(project,ProjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto deleteProject(Long id) {
        ProjectDto projectDto=modelMapper.map(projectRepository.findById(id).get(),ProjectDto.class);
        projectRepository.deleteById(id);
        return projectDto;
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project existingProject=projectRepository.findById(projectDto.getId()).get();
        existingProject.setDescription(projectDto.getDescription());
        existingProject.setEmployeeId(projectDto.getEmployeeId());
        existingProject.setName(projectDto.getName());
        existingProject.setEndDate(projectDto.getEndDate());
        existingProject.setStartDate(projectDto.getStartDate());
        existingProject.setIsDone(projectDto.getIsDone());
        projectRepository.save(existingProject);
        return projectDto;
    }

}
