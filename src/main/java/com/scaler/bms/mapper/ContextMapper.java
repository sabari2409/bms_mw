package com.scaler.bms.mapper;

/**
 * This file acts as base class for mapper from entity to dto and vice versa
 * This interface for single property
 * ContextMapper
 * @param <D> - DTO
 * @param <E> - Entity
 */
public interface ContextMapper<D, E> {

    E toEntity(D dto);

    D toDTO(E entity);
}
