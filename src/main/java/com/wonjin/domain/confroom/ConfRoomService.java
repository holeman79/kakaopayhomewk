package com.wonjin.domain.confroom;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfRoomService {
	@Autowired
	private ConfRoomRepository confRoomRespository;
	
	public List<ConfRoom> getConfRoomList(){
		return confRoomRespository.findAll();
	}
}
