package com.isr.test.service;

import com.isr.test.TestApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest(classes = {TestApplication.class})
public class LoginServiceIntegrationTest {

    @Autowired
    private LoginService loginService;

    private static List<String> attribute1List;

    private static List<String> attribute2List;

    private static List<String> attribute3List;

    private static List<String> attribute4List;

    @BeforeAll
    public static void init(){
        attribute1List = new ArrayList<>();
        attribute2List = new ArrayList<>();
        attribute3List = new ArrayList<>();
        attribute4List = new ArrayList<>();

        attribute1List.add("AA0");
        attribute1List.add("AA1");
        attribute1List.add("AA2");
        attribute1List.add("AA3");
        attribute1List.add("AA4");

        attribute2List.add("BB0");
        attribute2List.add("BB1");
        attribute2List.add("BB2");
        attribute2List.add("BB3");
        attribute2List.add("BB4");

        attribute3List.add("CC0");
        attribute3List.add("CC1");
        attribute3List.add("CC2");
        attribute3List.add("CC3");
        attribute3List.add("CC4");

        attribute4List.add("DD0");
        attribute4List.add("DD1");
        attribute4List.add("DD2");
        attribute4List.add("DD3");
        attribute4List.add("DD4");
    }

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
                LocalDate.now(), attribute1List, attribute2List, attribute3List, attribute4List))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute1() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, attribute1List, null, null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute2() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, attribute2List, null, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute3() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, null, attribute3List, null))
                .isNotNull().isNotEmpty();
    }

    @Test
    public void getUsersWithLoginCountByAttribute4() {
        assertThat(loginService.getUsersWithLoginCount(null,
                null, null, null, null, attribute4List))
                .isNotNull().isNotEmpty();
    }

}
