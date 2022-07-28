package aehdb.comm.model.mapper;

import java.util.List;

import org.mapstruct.Context;

public interface UpgradeGenericMapper<ITEM, ENTITY, REQ, RES> {
	ITEM reqToItem(REQ rq);
	ENTITY itemtoEntity(ITEM sourcei, @Context CycleAvoidingMappingContext context);
	ITEM entitiytoItem(ENTITY source, @Context CycleAvoidingMappingContext context);
	RES itemToRes (ITEM i);	
	
	List<ITEM> reqToItem(List<REQ> rq);
	List<RES> itemToRes(List<ITEM> i);
}
