package com.lab.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Convert DTO to Entity
     */
    public <D, T> T toEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Convert Entity to DTO
     */
    public <T, D> D toDTO(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
