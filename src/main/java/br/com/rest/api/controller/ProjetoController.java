package br.com.rest.api.controller;


import br.com.rest.api.domain.Projeto;
import br.com.rest.api.exceptions.ProjetoNaoEncontradoException;
import br.com.rest.api.input.ProjetoInput;
import br.com.rest.api.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.rest.api.output.ProjetoOutput;

import java.util.List;

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
    public ResponseEntity<ProjetoOutput> buscar(@PathVariable("id") Long id){
        try {
            final ProjetoOutput projetoOutput = new ProjetoOutput(this.projetoService.buscarUmProjeto(id));
            return ResponseEntity.ok(projetoOutput);
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projeto> incluir(@RequestBody @Validated ProjetoInput projetoInput){
        try {
            final Projeto newProjeto = this.projetoService.salvarProjeto(new Projeto(projetoInput));
            return new ResponseEntity<>(newProjeto, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ProjetoOutput> atualizar(ProjetoInput projetoInput){
        try {
            final ProjetoOutput projetoOutput = new ProjetoOutput(this.projetoService.atualizarProjeto(projetoInput));
            return new ResponseEntity<>(projetoOutput, HttpStatus.OK);
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Projeto> deletar(@PathVariable("id") Long id){
        try {
            this.projetoService.deletarProjetoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ProjetoNaoEncontradoException projetoNaoEncontradoException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
