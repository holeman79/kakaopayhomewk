package com.wonjin.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonjin.domain.confroom.ConfRoom;
import com.wonjin.domain.confroom.ConfRoomRepository;
import com.wonjin.domain.confroom.ConfRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ConfRoomControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ConfRoomRepository confRoomRepository;

    @Mock
    private ConfRoomService confRoomService;

    @InjectMocks
    private ConfRoomController confRoomController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(confRoomController)
                .addFilters(new CorsFilter())
                .build();
    }
    @Test
    public void testGetConfRoomList() throws Exception {
        List<ConfRoom> list =confRoomRepository.findAll();
        when(confRoomService.getConfRoomList()).thenReturn(list);
        mockMvc.perform(get("http://localhost:8080/api/confroom/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}