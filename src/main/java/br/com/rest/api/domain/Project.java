package br.com.rest.api.domain;

import br.com.rest.api.request.ProjectRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "TITLE")
    private String title;

    public Project() {
    }

    public Project(String owner, String title) {
        this.owner = owner;
        this.title = title;
    }

    public Project(ProjectRequest projectRequest) {
        this.id = projectRequest.getId();
        this.owner = projectRequest.getOwner();
        this.title = projectRequest.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void parseNewProjectInput(ProjectRequest projectNew){
        this.owner = projectNew.getOwner();
        this.title = projectNew.getTitle();
    }

}
