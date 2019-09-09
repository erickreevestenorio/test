package com.isr.test.controller;

import com.isr.test.service.LoginService;
import com.isr.test.web.rest.controller.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles(profiles = {"test"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @MockBean
    private LoginService loginService;

    @MockBean
    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDates_thenReturnJsonArrayOfLocalDates()
            throws Exception {
        LocalDate localDate = LocalDate.now();
        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(localDate);
        localDateList.add(localDate.minusDays(1));
        localDateList.add(localDate.minusDays(2));
        localDateList.add(localDate.minusDays(3));
        localDateList.add(localDate.minusDays(4));

        MvcResult mvcResult = mockMvc.perform(get("/dates")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"2019-09-08\"}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");
    }
}
