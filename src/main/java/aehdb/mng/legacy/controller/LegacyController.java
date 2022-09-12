package aehdb.mng.legacy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.model.mapper.LegacyMapper;
import aehdb.mng.legacy.model.dto.LegacyDto;
import aehdb.mng.legacy.service.LegacyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LegacyController {

	private final LegacyService legacyServiceImpl;
	private final LegacyMapper legacyMapper;

	@GetMapping(value = "/mng/legacies")
	public ResponseMap getLegacies(Pageable pageable) throws Exception {
		List<LegacyDto.Item> legacyList = legacyServiceImpl.selectLegacyList(pageable);
		List<LegacyDto.Response> legacyResList = legacyMapper.itemToRes(legacyList);
		return new ResponseMap(200, "", legacyResList);
	}

}
