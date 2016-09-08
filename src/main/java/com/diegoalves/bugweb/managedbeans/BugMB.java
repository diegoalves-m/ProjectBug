package com.diegoalves.bugweb.managedbeans;

import com.diegoalves.bugweb.model.Bug;
import com.diegoalves.bugweb.model.Project;
import com.diegoalves.bugweb.sessionbeans.BugRepository;
import com.diegoalves.bugweb.sessionbeans.ProjectRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author Diego Alves
 */
@Named
@RequestScoped
public class BugMB {

    @Inject
    private BugRepository bugRepository;
    @Inject
    private ProjectRepository projectRepository;
    private Bug bug = new Bug();
    private Long projectId;
    private List<Bug> bugs;

    @Transactional
    public void save() {
        Project project = projectRepository.findById(projectId);
        bug.setProject(project);

        if (getBug().getId() == null) {
            bugRepository.add(getBug());
        } else {
            bugRepository.edit(getBug());
        }
        this.bug = new Bug();
        this.bugs = null;
    }
    
    public void prepareEdit(Long id) {
        bug = bugRepository.findById(id);
    }
    
    @Transactional
    public void delete(Long id) {
        bugRepository.removeById(id);
        bugs = null;
    }

    public Bug getBug() {
        return bug;
    }

    public List<Bug> getBugs() {
        if(bugs == null) {
            bugs = bugRepository.findAll();
        }
        return bugs;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }
    
    public String backToProjects() {
        return "projects.xhtml";
    }
    
}
