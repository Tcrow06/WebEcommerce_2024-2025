package com.webecommerce.mapper;

import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.entity.product.CategoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper <DTO,Entity> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);

    // Default method to map a list of entities to a list of DTOs
    default List<DTO> toDTOList(List<Entity> entities) {
        if (entities == null) {
            new ArrayList<DTO>();
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Default method to map a list of DTOs to a list of entities
    default List<Entity> toEntityList(List<DTO> dtos) {
        if (dtos == null) {
            new ArrayList<Entity>();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
