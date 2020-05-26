package br.com.alunos.service;

import br.com.alunos.controller.request.AlunoRequest;
import br.com.alunos.service.model.AlunoServiceDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlunoService {
    List<AlunoServiceDto> buscarTodosAlunos();

    AlunoServiceDto bucarPorId(Long id);

    AlunoServiceDto criarAluno(AlunoRequest alunoRequest);

    AlunoServiceDto atualizarAluno(Long id, AlunoRequest alunoRequest);

    ResponseEntity<?> deletar(Long id);
}
