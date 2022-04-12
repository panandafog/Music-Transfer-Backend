package com.panandafog.mt_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.panandafog.mt_server.authorisation.TestUserDetails;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.dummy.DummiesController;
import com.panandafog.mt_server.dummy.DummiesService;
import com.panandafog.mt_server.dummy.Dummy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.lang.reflect.Array;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DummiesTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        Dummy dummy = new Dummy();
        dummy.setId(1);
        dummy.setName("test dummy");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dummy);

        Dummy array[] = new Dummy[] { dummy };
        String responseJson = ow.writeValueAsString(array);

        this.mockMvc.perform(
                        post("/dummies/save")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(requestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> this.mockMvc.perform(
                                get("/dummies/all")
                                        .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                        )
                                .andExpect(status().isOk())
                                .andExpect(content().json(responseJson))
                );
    }
}

