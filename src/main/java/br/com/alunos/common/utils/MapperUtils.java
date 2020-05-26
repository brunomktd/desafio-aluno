package br.com.alunos.common.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("mapper")
public class MapperUtils {
    private ModelMapper modelMapper;

    public MapperUtils(){
        this.modelMapper = new ModelMapper();
    }

    public <T> T converterObjeto(Object obj, Class<T> classOfT) {
        return this.modelMapper.map(obj, classOfT);
    }
}
