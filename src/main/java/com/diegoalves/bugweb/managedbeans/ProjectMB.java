package com.diegoalves.bugweb.managedbeans;

import com.diegoalves.bugweb.model.Project;
import com.diegoalves.bugweb.sessionbeans.ProjectRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego Alves
 */
@Named
@RequestScoped
public class ProjectMB {
    
    @Inject
    private ProjectRepository projectRepository;
    private Project project =  new Project();
    private List<Project> projects;
    
    public void save() {
        if(getProject().getId() == null) {
            projectRepository.addProject(project);
        } else {
            projectRepository.edit(project);
        }
        project = new Project();
        projects = null;
    }
    
    public void delete(Long id) {
        projectRepository.removeById(id);
        projects = null;
    }
    
    public void prepareEdit(Long id) {
        project = projectRepository.findById(id);
    }

    public List<Project> getProjects() {
        if(projects == null) {
            projects = projectRepository.findAll();
        }
        return projects;
    }

    public Project getProject() {
        return project;
    }
    
    public String toBugs() {
        return "bugs.xhtml";
    }
    
}
