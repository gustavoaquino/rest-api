package br.com.rest.api.controller;


import br.com.rest.api.domain.Project;
import br.com.rest.api.request.ProjectRequest;
import br.com.rest.api.response.ProjectResponse;
import br.com.rest.api.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAll(){
        final List<Project> projectList = this.projectService.findAllProject();

        return projectList.stream().map(project -> ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .owner(project.getOwner())
                .build()).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponse getById(@PathVariable("id") Long id){
            final Project project = this.projectService.findById(id);

            return ProjectResponse.builder()
                    .id(project.getId())
                    .owner(project.getOwner())
                    .title(project.getTitle())
                    .build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse save(@RequestBody ProjectRequest projectRequest){
        final Project project = this.projectService.saveProject(projectRequest);

        return ProjectResponse.builder().id(project.getId())
                .title(project.getTitle())
                .owner(project.getOwner())
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponse update(@RequestBody ProjectRequest projectRequest){
            final Project project = this.projectService.updateProject(projectRequest);

            return ProjectResponse.builder().id(project.getId())
                    .title(project.getTitle())
                    .owner(project.getOwner())
                    .build();
   }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        this.projectService.deleteProjectById(id);
    }


}
