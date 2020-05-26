package br.com.alunos.service.impl;

import br.com.alunos.common.utils.MapperUtils;
import br.com.alunos.controller.request.AlunoRequest;
import br.com.alunos.model.Aluno;
import br.com.alunos.repository.AlunoRepository;
import br.com.alunos.service.AlunoService;
import br.com.alunos.service.model.AlunoServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    /**
     * Estou tentando fazer simples,
     * nesse caso injetaria gateway e após repository
     */
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MapperUtils mapperUtils;

    @Override
    public List<AlunoServiceDto> buscarTodosAlunos() {
        var alunos =  alunoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        var lista = alunos.stream().map(
                aluno -> mapperUtils.converterObjeto(aluno, AlunoServiceDto.class)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public AlunoServiceDto bucarPorId(Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            return mapperUtils.converterObjeto(alunoOptional.get(), AlunoServiceDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public AlunoServiceDto criarAluno(AlunoRequest alunoRequest) {
        var novoAluno = mapperUtils.converterObjeto(alunoRequest, Aluno.class);
        return mapperUtils.converterObjeto(alunoRepository.save(novoAluno), AlunoServiceDto.class);
    }

    @Override
    public AlunoServiceDto atualizarAluno(Long id, AlunoRequest alunoRequest) {
        boolean alunoExists = alunoRepository.existsById(id);

        if(alunoExists){
            var aluno = mapperUtils.converterObjeto(alunoRequest, Aluno.class);
            alunoRepository.save(aluno);
            return mapperUtils.converterObjeto(aluno, AlunoServiceDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deletar(Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            alunoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
