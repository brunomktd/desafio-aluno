package br.com.alunos.controller;

import br.com.alunos.common.utils.MapperUtils;
import br.com.alunos.controller.request.AlunoRequest;
import br.com.alunos.controller.response.AlunoResponse;
import br.com.alunos.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MapperUtils mapperUtils;

    @GetMapping
    public List<AlunoResponse> buscarTodos(){
        var alunos = alunoService.buscarTodosAlunos();
        return alunos.stream().map(
                aluno -> mapperUtils.converterObjeto(aluno, AlunoResponse.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id){
        var aluno = mapperUtils.converterObjeto(alunoService.bucarPorId(id), AlunoResponse.class);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criarAluno(@RequestBody @Valid AlunoRequest alunoRequest, UriComponentsBuilder uriBuilder){
        var aluno = mapperUtils.converterObjeto(alunoService.criarAluno(alunoRequest), AlunoResponse.class);

        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoRequest alunoRequest){
        var aluno = mapperUtils.converterObjeto(alunoService.atualizarAluno(id, alunoRequest), AlunoResponse.class);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerAluno(@PathVariable Long id){
        return alunoService.deletar(id);
    }
}
