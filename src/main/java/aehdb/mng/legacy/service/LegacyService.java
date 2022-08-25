package aehdb.mng.legacy.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import aehdb.mng.legacy.model.dto.LegacyDto;

public interface LegacyService {
	public List<LegacyDto.Item> selectLegacyList(Pageable pageable) throws Exception;

}
