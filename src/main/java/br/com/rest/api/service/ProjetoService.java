package br.com.rest.api.service;

import br.com.rest.api.domain.Projeto;
import br.com.rest.api.exceptions.ProjetoNaoEncontradoException;
import br.com.rest.api.input.ProjetoInput;
import br.com.rest.api.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto buscarUmProjeto(Long id) throws ProjetoNaoEncontradoException {
        final Optional<Projeto> projetoOptional = this.projetoRepository.findById(id);
        if(!projetoOptional.isPresent()){
            throw new ProjetoNaoEncontradoException();
        }
        return projetoOptional.get();
    }

    public List<Projeto> listarProjeto() throws ProjetoNaoEncontradoException {
        final List<Projeto> projetoList = this.projetoRepository.findAll();
        if(projetoList.isEmpty()){
            throw new ProjetoNaoEncontradoException();
        }
        return this.projetoRepository.findAll();
    }

    public Projeto salvarProjeto(Projeto projeto){
        return this.projetoRepository.save(projeto);
    }

    public Projeto atualizarProjeto(ProjetoInput projetoInput) throws ProjetoNaoEncontradoException {
        final Projeto projetoNew = new Projeto(projetoInput);
        Projeto projetoOld = this.buscarUmProjeto(projetoInput.getId());
        projetoOld = projetoNew;
        return this.projetoRepository.save(projetoOld);
    }

    public void deletarProjetoById(Long id) throws ProjetoNaoEncontradoException {
        final Projeto projeto = this.buscarUmProjeto(id);
        this.projetoRepository.delete(projeto);
    }


}
