package br.com.alunos.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AlunoResponse {
    private Long id;
    private String nome;
    private Integer idade;
}
