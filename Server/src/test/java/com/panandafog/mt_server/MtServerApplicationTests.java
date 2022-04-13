package com.panandafog.mt_server;

import com.panandafog.mt_server.music.LastFmTests;
import com.panandafog.mt_server.music.VKTests;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LastFmTests.class,
        VKTests.class,
        DummiesTests.class
})
@SpringBootTest
class MtServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
