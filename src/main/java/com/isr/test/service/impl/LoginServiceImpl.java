package com.isr.test.service.impl;

import com.isr.test.model.dto.UserWithLoginCountDTO;
import com.isr.test.repository.LoginRepository;
import com.isr.test.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public List<LocalDate> findDistinctLoginTimeOrderByLoginTimeAsc() {
        return loginRepository.findDistinctLoginTimeOrderByLoginTimeAsc();
    }

    @Override
    public List<String> findDistinctUsersByStartAndEndDateOrderByUserAsc(LocalDate startDate, LocalDate endDate) {
        List<String> listOfUser = loginRepository.findDistinctUsersByStartAndEndDateOrderByUserAsc(startDate, endDate);
        if (listOfUser.isEmpty()) {
            listOfUser = null;
        }
        return listOfUser;
    }

    @Override
    public List<UserWithLoginCountDTO> getUsersWithLoginCount(LocalDate startDate, LocalDate endDate,
                                                              List<String> attribute1, List<String> attribute2,
                                                              List<String> attribute3, List<String> attribute4) {
        List<UserWithLoginCountDTO> userWithLoginCountDTOList;
        userWithLoginCountDTOList = loginRepository
                .getUsersWithLoginCount(startDate, endDate, attribute1, attribute2, attribute3,
                        attribute4);
        if (userWithLoginCountDTOList.isEmpty()) {
            userWithLoginCountDTOList = null;
        }
        return userWithLoginCountDTOList;
    }
}
