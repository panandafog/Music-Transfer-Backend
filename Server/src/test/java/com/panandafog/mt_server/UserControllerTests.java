package com.panandafog.mt_server;

import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.panandafog.mt_server.controller.DummiesController;
import com.panandafog.mt_server.controller.UsersController;
import com.panandafog.mt_server.entity.Dummy;
import com.panandafog.mt_server.entity.User;
import com.panandafog.mt_server.service.DummiesService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(true)
@RunWith(SpringRunner.class)
@WebMvcTest(DummiesController.class)
public class UserControllerTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

//    @Autowired
//    private TestEntityManager entityManager;

//    private MockMvc mvc;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private DummiesService service;

//    @Before("")
//    public void setup() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void testCreateUser() throws Exception {
//        User user = new User();
//        user.setEmail("testuser1@gmail.com");
//        user.setPassword("testpassword1");
//        user.setFirstName("testusername1");
//        user.setLastName("testuserlastname1");


        Dummy dummy = new Dummy();
        dummy.setName("test dummy");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dummy);

        mvc.perform(
                post("/dummies/save")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson)
        )
                .andExpect(status().isOk());
    }
}
