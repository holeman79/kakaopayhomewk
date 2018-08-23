package com.wonjin.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wonjin.KakaoPayHwApplication;
import com.wonjin.domain.confroom.ConfRoom;
import com.wonjin.domain.confroom.ConfRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KakaoPayHwApplication.class)
@DataJpaTest
@ComponentScan("com.wonjin.domain")
public class ConfRoomServiceTest {

	@Autowired
	private ConfRoomService confRoomService;
	
	static final int numOfConfRoom = 6;

	@Test
	public void testGetConfRoomList() {
		List<ConfRoom> result = confRoomService.getConfRoomList();
		assertTrue(result.size() == numOfConfRoom);
	}

}
