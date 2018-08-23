package com.wonjin.controller;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.List;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonjin.KakaoPayHwApplication;
import com.wonjin.domain.dto.ReserveInfoRequestDto;
import com.wonjin.domain.reserveinfo.ReserveInfo;
import com.wonjin.domain.reserveinfo.ReserveInfoRepository;
import com.wonjin.domain.reserveinfo.ReserveInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ReserveInfoControllerTest {
	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ReserveInfoRepository reserveInfoRepository;
	
	@Mock
	private ReserveInfoService reserveInfoService;
	
	@InjectMocks
	private ReserveInfoController reserveInfoController;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(reserveInfoController)
				.addFilters(new CorsFilter())
				.build();
	}

	@Test
	public void testGetReserveInfoRequestList() throws Exception{
		ReserveInfo reserveInfo = new ReserveInfo();
		reserveInfo.setConfId(2);
		reserveInfo.setDate("20180823");
		reserveInfo.setId(1L);
		reserveInfo.setRepeatCount(2);
		reserveInfo.setReserver("muzi");
		reserveInfo.settimeFrom("08:00");
		reserveInfo.settimeTo("09:00");
		reserveInfoRepository.save(reserveInfo);
		List<ReserveInfoRequestDto> list = reserveInfoRepository.getReserveRequestList("20180823");
		when(reserveInfoService.getReserveInfoRequestList("20180823")).thenReturn(list);
		this.mockMvc.perform(get("http://localhost:8080/api/reserveinfo/{date}", "20180823")
				.contentType(contentType))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testInsertReserveInfoRequest() throws Exception{
		ReserveInfo reserveInfo = new ReserveInfo();
		reserveInfo.setConfId(2);
		reserveInfo.setDate("20180823");
		reserveInfo.setId(1L);
		reserveInfo.setRepeatCount(2);
		reserveInfo.setReserver("muzi");
		reserveInfo.settimeFrom("08:00");
		reserveInfo.settimeTo("09:00");
		when(reserveInfoService.reserveCheck(reserveInfo)).thenReturn(true);
		this.mockMvc.perform(post("http://localhost:8080/api/reserveinfo/")
				.contentType(contentType)
				.content(objectMapper.writeValueAsString(reserveInfo)))
				.andExpect(status().isOk())
				.andDo(print());
		verify(reserveInfoService, times(1)).insertReserveInfoRequest(refEq(reserveInfo));
        verifyNoMoreInteractions(reserveInfoService);
	}

}
