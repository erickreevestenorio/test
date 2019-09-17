package com.isr.test.runner;

import com.isr.test.model.domain.LoginEntity;
import com.isr.test.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Profile(value = "test")
public class TestCommandLineRunner implements CommandLineRunner {

    private final LoginRepository loginRepository;

    /**
     * It will persist 100000 data into Login Table
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        LoginEntity loginEntity;
        int count = 0;
        while (count < 100000) {
            loginEntity = new LoginEntity();
            loginEntity.setUser("user"+count);
            if (count % 2 == 0) {
                loginEntity.setUser("user" + count);
            }
            loginEntity.setAttribute1("AA" + count);
            loginEntity.setAttribute2("BB" + count);
            loginEntity.setAttribute3("CC" + count);
            loginEntity.setAttribute4("DD" + count);
            loginEntity.setLoginTime(LocalDateTime.now().minusHours(Long.parseLong(String.valueOf(count))));
            loginRepository.save(loginEntity);
            count++;
        }

    }
}
