package br.com.rest.api.output;

import br.com.rest.api.domain.Projeto;

public class ProjetoOutput {

    private Long id;
    private String owner;
    private String title;

    public ProjetoOutput(Projeto projeto) {
        this.id = projeto.getId();
        this.owner = projeto.getOwner();
        this.title = projeto.getTitle();

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
