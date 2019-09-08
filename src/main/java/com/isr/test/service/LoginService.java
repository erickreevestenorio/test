package com.isr.test.service;

import com.isr.test.model.dto.UserWithLoginCountDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoginService {

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of LocalDate array of all the unique dates (ignoring time) in the table
     * The resulted List of LocalDate is sorted in ascending order.
     */
    List<LocalDate> findDistinctLoginTimeOrderByLoginTimeAsc();

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
    List<String> findDistinctUsersByStartAndEndDateOrderByUserAsc(LocalDate startDate, LocalDate endDate);

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
    List<UserWithLoginCountDTO> getUsersWithLoginCount(LocalDate startDate, LocalDate endDate, List<String> attribute1,
                                                       List<String> attribute2, List<String> attribute3, List<String> attribute4);
}
