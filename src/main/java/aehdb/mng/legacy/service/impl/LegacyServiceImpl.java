package aehdb.mng.legacy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import aehdb.comm.model.mapper.LegacyMapper;
import aehdb.mng.legacy.model.dto.LegacyDto.Item;
import aehdb.mng.legacy.model.entity.Legacy;
import aehdb.mng.legacy.model.repository.LegacyRepository;
import aehdb.mng.legacy.service.LegacyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegacyServiceImpl implements LegacyService {
	private final LegacyRepository legacyRepository;
	private final LegacyMapper legacyMapper;

	public List<Item> selectLegacyList(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		List<Item> legacyItemList = new ArrayList<Item>();
		Page<Legacy> legacyEntity = legacyRepository.findAll(pageable);
		
		for(Legacy l : legacyEntity)
			
			legacyItemList.add(legacyMapper.entitiytoItem(l, new CycleAvoidingMappingContext()));
		
		return legacyItemList;
	}

}
