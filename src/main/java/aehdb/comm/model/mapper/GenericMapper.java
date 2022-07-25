package aehdb.comm.model.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper <D,E>{
	D toDto(E e);
	E toEntity(D d);
	
	
	
	List<D> toDto(List<E> e);
	List<E> toEntity(List<D> d);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateFromDto(D dto, @MappingTarget E entity);

}
