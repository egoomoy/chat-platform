package aehdb.comm.model.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface UpgradeGenericMapper<ITEM, ENTITY, REQ, RES> {
	ITEM reqToItem(REQ rq);
	ENTITY itemtoEntity(ITEM i);
	ITEM entitiytoItem(ENTITY e);
	RES itemToRes (ITEM i);	
	
	List<ITEM> reqToItem(List<REQ> rq);
	List<ENTITY> itemtoEntity(List<ITEM> i);
	List<ITEM> entitiytoItem(List<ENTITY> e);
	List<RES> itemToRes(List<ITEM> i);
}
