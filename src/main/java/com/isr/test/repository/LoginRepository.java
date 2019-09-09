package com.isr.test.repository;

import com.isr.test.model.domain.LoginEntity;
import com.isr.test.model.dto.UserWithLoginCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of LocalDate array of all the unique dates (ignoring time) in the table
     * The resulted List of LocalDate is sorted in ascending order.
     */
    @Query(value = "select distinct l.loginDate from LoginEntity l order by l.loginDate asc ")
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
    @Query(
            value = "select distinct l.user from LoginEntity l where (:startDate is null or l.loginDate >= :startDate) " +
                    "and (:endDate is null or l.loginDate <= :endDate) group by l.user order by l.user asc ")
    List<String> findDistinctUsersByStartAndEndDateOrderByUserAsc(@Param(value = "startDate") LocalDate startDate,
                                                                  @Param(value = "endDate") LocalDate endDate);


    /**
     * {@inheritDoc}
     * <p>
     * Retrieves a List of {@link UserWithLoginCountDTO} where the key is the user name and the value is the number of
     * times a user logged on between the start and the end date.
     * All parameters are optional.
     * The values used for the attributes are used as filters
     *
     * @param startDate      a {@link LocalDate} object.
     * @param endDate        a {@link LocalDate} object.
     * @param attribute1List a {@link List<String>} object.
     * @param attribute2List a {@link List<String>} object.
     * @param attribute3List a {@link List<String>} object.
     * @param attribute4List a {@link List<String>} object.
     * @return a List of {@link UserWithLoginCountDTO} object
     */
    @Query(value = "SELECT new com.isr.test.model.dto.UserWithLoginCountDTO(l.user, count(l.user)) from LoginEntity l " +
            "where (:startDate is null or l.loginDate >= :startDate) and (:endDate is null or l.loginDate <= :endDate) " +
            " and ((:attribute1) is null or l.attribute1 in (:attribute1)) and" +
            "((:attribute2) is null or l.attribute2 in (:attribute2)) and " +
            "((:attribute3) is null or l.attribute3 in (:attribute3)) and " +
            "((:attribute4) is null or l.attribute4 in (:attribute4)) group by l.user")
    List<UserWithLoginCountDTO> getUsersWithLoginCount(@Param(value = "startDate") LocalDate startDate,
                                                       @Param(value = "endDate") LocalDate endDate,
                                                       @Param(value = "attribute1") List<String> attribute1List,
                                                       @Param(value = "attribute2") List<String> attribute2List,
                                                       @Param(value = "attribute3") List<String> attribute3List,
                                                       @Param(value = "attribute4") List<String> attribute4List);
}
