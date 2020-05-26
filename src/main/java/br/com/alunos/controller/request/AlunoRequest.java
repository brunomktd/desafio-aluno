package br.com.alunos.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Getter
@Setter
public class AlunoRequest {
    @NotNull @NotEmpty
    @Length(min = 5)
    private String nome;
    @NotNull @Min(0)
    private Integer idade;
}
