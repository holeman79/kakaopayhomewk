package com.wonjin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wonjin.domain.confroom.ConfRoom;
import com.wonjin.domain.confroom.ConfRoomService;

@RestController
@RequestMapping(value = "/api/confroom")
public class ConfRoomController {
	
	@Autowired
	private ConfRoomService confRoomService;
	
	@GetMapping("/")
	public List<ConfRoom> getConfRoomList(){
		return confRoomService.getConfRoomList();
	}
}
