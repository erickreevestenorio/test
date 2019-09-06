package com.isr.test.service;

import com.isr.test.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest(classes = {TestApplication.class})
public class LoginServiceIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void testFindDistinctLoginTimeOrderByLoginTimeAsc() {
        assertThat(loginService.findDistinctLoginTimeOrderByLoginTimeAsc()).isNotNull().isNotEmpty();
    }

    @Test
    public void testFindDistinctUsersByStartAndEndDateOrderByUserAsc() {
        assertThat(loginService.findDistinctUsersByStartAndEndDateOrderByUserAsc(LocalDate.now().minusDays(10),
                LocalDate.now())).isNotNull().isNotEmpty();
    }

    @Test
    public void testFindDistinctUsersByStartAndEndDateOrderByUserAscNullStartDate() {
        assertThat(loginService.findDistinctUsersByStartAndEndDateOrderByUserAsc(null,
                LocalDate.now())).isNotNull().isNotEmpty();
    }

    @Test
    public void testFindDistinctUsersByStartAndEndDateOrderByUserAscNullEndDate() {
        assertThat(loginService.findDistinctUsersByStartAndEndDateOrderByUserAsc(LocalDate.now().minusDays(10),
                null)).isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByStartDate() {
        assertThat(loginService.getUsersWithLoginCount(LocalDate.now().minusDays(10),
                LocalDate.now(), null, null, null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByEndDate() {
        assertThat(loginService.getUsersWithLoginCount(null,
                LocalDate.now(), null, null, null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCount() {
        assertThat(loginService.getUsersWithLoginCount(LocalDate.now().minusDays(10),
                LocalDate.now(), "AA0", "BB0", "CC0", "DD0"))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute1() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, "AA0", null, null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute2() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, "BB0", null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute3() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, null, "CC0", null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute4() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, null, null, "DD0"))
                .isNotNull().isNotEmpty();
    }

}
