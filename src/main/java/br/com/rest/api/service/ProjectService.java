package br.com.rest.api.service;

import br.com.rest.api.domain.Project;
import br.com.rest.api.exception.ProjectNotFoundException;
import br.com.rest.api.repository.ProjectRepository;
import br.com.rest.api.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project findById(Long id) {
        final Optional<Project> projectOptional = this.projectRepository.findById(id);
        if(projectOptional.isEmpty()){
            throw new ProjectNotFoundException();
        }
        return projectOptional.get();
    }

    public List<Project> findAllProject() {
        final List<Project> projectList = this.projectRepository.findAll();
        if(projectList.isEmpty()){
            throw new ProjectNotFoundException();
        }
        return this.projectRepository.findAll();
    }

    public Project saveProject(ProjectRequest projectRequest){
        final Project project = new Project(projectRequest);
        return this.projectRepository.save(project);
    }

    public Project updateProject(ProjectRequest projectInput) throws ProjectNotFoundException {
        final Project projectOld = this.findById(projectInput.getId());
        projectOld.parseNewProjectInput(projectInput);
        return this.projectRepository.save(projectOld);
    }

    public void deleteProjectById(Long id) {
        final Project project = this.findById(id);
        this.projectRepository.delete(project);
    }


}
