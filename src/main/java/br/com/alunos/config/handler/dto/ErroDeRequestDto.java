package br.com.alunos.config.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDeRequestDto {
    private String campo;
    private String erro;
}
