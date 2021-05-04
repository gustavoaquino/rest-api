package br.com.rest.api.controller;


import br.com.rest.api.domain.Projeto;
import br.com.rest.api.exceptions.ProjetoNaoEncontradoException;
import br.com.rest.api.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<Projeto>> listar(){
        try {
            final List<Projeto> projetoList = this.projetoService.listarProjeto();
            return ResponseEntity.ok(projetoList);
        }catch(ProjetoNaoEncontradoException projetoNaoEncontradoException){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable("id") Long id){
        try {
            final Optional<Projeto> projeto = this.projetoService.buscarUmProjeto(id);
            return ResponseEntity.ok(projeto.get());
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projeto> incluir(@RequestBody Projeto projeto){
        try {
            final Projeto newProjeto = this.projetoService.salvarProjeto(projeto);
            return new ResponseEntity<>(newProjeto, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Projeto> atualizar(@RequestBody Projeto projeto){
        try {
            Optional<Projeto> projetoOptional = this.projetoService.buscarUmProjeto(projeto.getId());
            Projeto oldProjeto = projetoOptional.get();
            oldProjeto = projeto;
            this.projetoService.salvarProjeto(oldProjeto);
            return new ResponseEntity<>(projeto, HttpStatus.OK);
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(projeto, HttpStatus.NOT_FOUND);
        }
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Projeto> deletar(@PathVariable("id") Long id){
        try {
            final Optional<Projeto> projetoOptional = this.projetoService.buscarUmProjeto(id);
            this.projetoService.deletarProjeto(projetoOptional.get());return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
