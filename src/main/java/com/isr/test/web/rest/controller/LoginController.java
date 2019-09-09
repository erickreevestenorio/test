package com.isr.test.web.rest.controller;

import com.isr.test.model.dto.UserWithLoginCountDTO;
import com.isr.test.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(name = "/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final LoginService loginService;

    private final HttpHeaders httpHeaders;

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of LocalDate array of all the unique dates (ignoring time) in the table
     * The resulted List of LocalDate is sorted in ascending order.
     */
    @ApiOperation(value = "Retrieves a List of LocalDate array of all the unique dates (ignoring time) in the table." +
            "The resulted List of LocalDate is sorted in ascending order.")
    @GetMapping(value = "dates")
    public ResponseEntity<List<LocalDate>> getDates() {
        return new ResponseEntity<>(loginService.findDistinctLoginTimeOrderByLoginTimeAsc(), httpHeaders, HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a list of user of all the unique users for which there is a login record between the start and end date.
     * Both parameters are optional, so there can be a start date, an end date, or both.
     * The resulted List of user is sorted in ascending order.
     *
     * @param startDate a {@link LocalDate} object.
     * @param endDate   a {@link LocalDate} object.
     * @return the List of users.
     */
    @ApiOperation(value = "Retrieves a list of user of all the unique users for which there is a login record " +
            "between the start and end date. Both parameters are optional, so there can be a start date, an end date, " +
            "or both. The resulted List of user is sorted in ascending order.")
    @GetMapping(value = "users")
    public ResponseEntity<List<String>> getUsers(
            @ApiParam(value = "Start Date. The format is yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd") @RequestParam(value = "start", required = false) LocalDate startDate,
            @ApiParam(value = "End Date. The format is yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd") @RequestParam(value = "end", required = false) LocalDate endDate) {
        return new ResponseEntity<>(loginService.findDistinctUsersByStartAndEndDateOrderByUserAsc(startDate, endDate), httpHeaders,
                HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of {@link UserWithLoginCountDTO} where the key is the user name and the value is the number of
     * times a user logged on between the start and the end date.
     * All parameters are optional.
     * The values used for the attributes are used as filters
     *
     * @param startDate  a {@link LocalDate} object.
     * @param endDate    a {@link LocalDate} object.
     * @param attribute1 a {@link String} object.
     * @param attribute2 a {@link String} object.
     * @param attribute3 a {@link String} object.
     * @param attribute4 a {@link String} object.
     * @return a List of {@link UserWithLoginCountDTO} object
     */
    @ApiOperation(value = "Retrieves a List of {@link UserWithLoginCountDTO} where the key is the user name and " +
            "the value is the number of times a user logged on between the start and the end date." +
            "All parameters are optional. The values used for the attributes are used as filters")
    @GetMapping(value = "logins")
    public ResponseEntity<List<UserWithLoginCountDTO>> getLogins(
            @ApiParam(value = "Start Date. The format is yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd") @RequestParam(value = "start", required = false) LocalDate startDate,
            @ApiParam(value = "End Date. The format is yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd") @RequestParam(value = "end", required = false) LocalDate endDate,
            @ApiParam(value = "List of Attribute1")
            @RequestParam(value = "attribute1", required = false) List<String> attribute1,
            @ApiParam(value = "List of Attribute2")
            @RequestParam(value = "attribute2", required = false) List<String> attribute2,
            @ApiParam(value = "List Attribute3")
            @RequestParam(value = "attribute3", required = false) List<String> attribute3,
            @ApiParam(value = "List of Attribute4")
            @RequestParam(value = "attribute4", required = false) List<String> attribute4) {
        return new ResponseEntity<>(loginService.getUsersWithLoginCount(startDate, endDate, attribute1, attribute2,
                attribute3, attribute4), httpHeaders, HttpStatus.OK);
    }

}
