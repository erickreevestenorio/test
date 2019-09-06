package com.isr.test.repository;

import com.isr.test.model.domain.LoginEntity;
import com.isr.test.model.dto.UserWithLoginCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.LinkedList;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of LocalDate array of all the unique dates (ignoring time) in the table
     * The resulted List of LocalDate is sorted in ascending order.
     */
    @Query(value = "select distinct l.loginDate from LoginEntity l order by l.loginDate asc ")
    LinkedList<LocalDate> findDistinctLoginTimeOrderByLoginTimeAsc();

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
    @Query(
            value = "select distinct l.user from LoginEntity l where (:startDate is null or l.loginDate >= :startDate) " +
                    "and (:endDate is null or l.loginDate <= :endDate) group by l.user order by l.user asc ")
    LinkedList<String> findDistinctUsersByStartAndEndDateOrderByUserAsc(@Param(value = "startDate") LocalDate startDate,
                                                                        @Param(value = "endDate") LocalDate endDate);


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
    @Query(value = "SELECT new com.isr.test.model.dto.UserWithLoginCountDTO(l.user, count(l.user)) from LoginEntity l " +
            "where (:startDate is null or l.loginDate >= :startDate) and (:endDate is null or l.loginDate <= :endDate) " +
            "and (:attribute1 is null or l.attribute1 = :attribute1) and " +
            "(:attribute2 is null or l.attribute2 = :attribute2) and (:attribute3 is null or l.attribute3 = :attribute3) " +
            " and (:attribute4 is null or l.attribute4 = :attribute4) group by l.user")
    LinkedList<UserWithLoginCountDTO> getUsersWithLoginCount(@Param(value = "startDate") LocalDate startDate,
                                                             @Param(value = "endDate") LocalDate endDate,
                                                             @Param(value = "attribute1") String attribute1,
                                                             @Param(value = "attribute2") String attribute2,
                                                             @Param(value = "attribute3") String attribute3,
                                                             @Param(value = "attribute4") String attribute4);
}
