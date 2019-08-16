package com.summ.debook.service.converter;

/**
 * @author Serhii Tymoshenko
 */
public interface Converter<DTO, ENTITY> {
    ENTITY convertDtoToEntity(DTO dto);
    DTO convertEntityToDto (ENTITY entity);
}
