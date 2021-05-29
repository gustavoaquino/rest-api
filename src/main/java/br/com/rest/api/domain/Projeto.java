package br.com.rest.api.domain;

import br.com.rest.api.input.ProjetoInput;

import javax.persistence.*;

@Entity
@Table(name = "PROJETO")
public class Projeto{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "TITLE")
    private String title;

    public Projeto() {
    }

    public Projeto(ProjetoInput projetoInput) {
        this.id = projetoInput.getId();
        this.owner = projetoInput.getOwner();
        this.title = projetoInput.getTitle();
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
}
