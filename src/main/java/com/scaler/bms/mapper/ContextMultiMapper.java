package com.scaler.bms.mapper;

/**
 * This file acts as base class for mapper from entity to dto and vice-versa
 * ContextMapper
 *
 * @param <C> - Context - Contains multiple Parameters
 * @param <D> - DTO
 * @param <E> - Entity
 */
public interface ContextMultiMapper<C, D, E> {

    E toEntity(C context);
    D toDTO(E entity);
}