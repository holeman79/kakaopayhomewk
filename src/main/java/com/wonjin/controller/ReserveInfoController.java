package com.wonjin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wonjin.domain.dto.ReserveInfoRequestDto;
import com.wonjin.domain.reserveinfo.ReserveInfo;
import com.wonjin.domain.reserveinfo.ReserveInfoService;

@RestController
@RequestMapping(value = "/api/reserveinfo")
public class ReserveInfoController {
	@Autowired
	private ReserveInfoService reserveInfoService;
	
	@GetMapping("/{date}")
	public List<ReserveInfoRequestDto> getReserveInfoRequestList(@PathVariable String date){
		return reserveInfoService.getReserveInfoRequestList(date);
	}
	@PostMapping("/")
	public ReserveInfo insertReserveInfoRequest(@RequestBody ReserveInfo reserveInfo) {
		return reserveInfoService.insertReserveInfoRequest(reserveInfo);
	}
	
}
