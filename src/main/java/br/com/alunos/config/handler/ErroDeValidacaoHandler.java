package br.com.alunos.config.handler;

import br.com.alunos.config.handler.dto.ErroDeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeRequestDto> handle(MethodArgumentNotValidException exception) {
        List<ErroDeRequestDto> dto = new ArrayList<>();
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            var mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeRequestDto erro = new ErroDeRequestDto(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }
}
