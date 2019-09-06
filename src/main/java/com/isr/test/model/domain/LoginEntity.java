package com.isr.test.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login", indexes = {
        @Index(name = "IDX_MYIDX1", columnList = "login_time,login_date, user")
})
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "login_time")
    private LocalDateTime loginTime;
    @Column(name = "login_date")
    private LocalDate loginDate;
    @Column(name = "user")
    private String user;
    @Column(name = "attribute1")
    private String attribute1;
    @Column(name = "attribute2")
    private String attribute2;
    @Column(name = "attribute3")
    private String attribute3;
    @Column(name = "attribute4")
    private String attribute4;


}
