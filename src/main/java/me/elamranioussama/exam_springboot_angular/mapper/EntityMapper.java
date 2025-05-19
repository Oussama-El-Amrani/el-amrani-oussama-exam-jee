package me.elamranioussama.exam_springboot_angular.mapper;

/**
 * Generic mapper interface that provides methods for mapping between entity and DTO objects
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}
