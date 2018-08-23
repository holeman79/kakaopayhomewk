package com.wonjin.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wonjin.domain.dto.ReserveInfoRequestDto;
import com.wonjin.domain.reserveinfo.ReserveInfo;
import com.wonjin.domain.reserveinfo.ReserveInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ComponentScan("com.wonjin.domain")
public class ReserveInfoServiceTest {
	@Autowired
	ReserveInfoService reserveInfoService;
	@Test
	public void testGetReserveInfoRequestList() {
		ReserveInfo item = new ReserveInfo();
		item.setDate("20180823");
		item.setConfId(1);
		item.setRepeatCount(4);
		item.setReserver("muzi");
		item.settimeFrom("15:00");
		item.settimeTo("16:30");
		reserveInfoService.insertReserveInfoRequest(item);
		
		item = new ReserveInfo();
		item.setDate("20180823");
		item.setConfId(2);
		item.setRepeatCount(3);
		item.setReserver("frodo");
		item.settimeFrom("12:00");
		item.settimeTo("15:00");
		reserveInfoService.insertReserveInfoRequest(item);
		List<ReserveInfoRequestDto> result = reserveInfoService.getReserveInfoRequestList("20180823");
		assertTrue(result.get(0).getConfName().equals("roomA"));
		assertTrue(result.size() == 2);
		
	}

	@Test
	public void testInsertReserveInfoRequest() {
		ReserveInfo item = new ReserveInfo();
		item.setDate("20180823");
		item.setConfId(1);
		item.setRepeatCount(4);
		item.setReserver("muzi");
		item.settimeFrom("15:00");
		item.settimeTo("16:30");
		ReserveInfo result = reserveInfoService.insertReserveInfoRequest(item);
		assertTrue(item.getReserver().equals(result.getReserver()));
	}

	@Test
	public void testReserveCheck() {
		ReserveInfo item = new ReserveInfo();
		item.setDate("20180823");
		item.setConfId(1);
		item.setRepeatCount(1);
		item.setReserver("ryan");
		item.settimeFrom("13:00");
		item.settimeTo("16:30");
		reserveInfoService.insertReserveInfoRequest(item);
		
		ReserveInfo check = new ReserveInfo();
		check.setDate("20180823");
		check.setConfId(1);
		check.setRepeatCount(1);
		check.setReserver("apeach");
		check.settimeFrom("15:00");
		check.settimeTo("16:00");
		assertFalse(reserveInfoService.reserveCheck(check));
	}

	@Test
	public void testReserveCheckRepeat() {
		ReserveInfo item = new ReserveInfo();
		item.setDate("20180823");
		item.setConfId(1);
		item.setRepeatCount(4);
		item.setReserver("frodo");
		item.settimeFrom("11:00");
		item.settimeTo("12:30");
		reserveInfoService.insertReserveInfoRequest(item);
		
		ReserveInfo check = new ReserveInfo();
		check.setDate("20180830");
		check.setConfId(1);
		check.setRepeatCount(1);
		check.setReserver("tube");
		check.settimeFrom("11:00");
		check.settimeTo("12:30");
		assertFalse(reserveInfoService.reserveCheckRepeat(check, null));
	}

}
