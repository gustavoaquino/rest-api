package br.com.rest.api.service;

import br.com.rest.api.domain.Projeto;
import br.com.rest.api.exceptions.ProjetoNaoEncontradoException;
import br.com.rest.api.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarProjeto() throws ProjetoNaoEncontradoException {
        final List<Projeto> projetoList = this.projetoRepository.findAll();
        if(projetoList.isEmpty()){
            throw new ProjetoNaoEncontradoException();
        }
        return this.projetoRepository.findAll();
    }

    public Optional<Projeto> buscarUmProjeto(Long id) throws ProjetoNaoEncontradoException {
        final Optional<Projeto> projetoOptional = this.projetoRepository.findById(id);
        if(!projetoOptional.isPresent()){
            throw new ProjetoNaoEncontradoException();
        }
        return this.projetoRepository.findById(id);
    }

    public Projeto salvarProjeto(Projeto projeto){
        return this.projetoRepository.save(projeto);
    }

    public void deletarProjeto(Projeto projeto) {
        this.projetoRepository.delete(projeto);
    }


}
