package com.panandafog.mt_server.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.TestUserDetails;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.exceptions.CustomException;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.DTO.vk.*;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VKTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveOperation() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        VKAddTracksOperationDTO addTracksOperation = new VKAddTracksOperationDTO(
                1,
                new Date(),
                new Date(),
                new VKSearchTracksSuboperationDTO(
                        1,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKSearchedTrackDTO(
                                        1,
                                        true,
                                        new SharedTrackDTO(
                                                1,
                                                "title 1",
                                                "spotifyID 1",
                                                "lastFmID 1",
                                                "vkID 1",
                                                "vkOwnerID 1",
                                                Arrays.asList("foo", "bar"),
                                                60
                                        ),
                                        new HashSet<>(List.of(
                                                new VKSavedItemDTO(
                                                        1,
                                                        "title 1",
                                                        "artist 1",
                                                        1,
                                                        60
                                                )
                                        ))
                                )
                        ))
                ),
                new VKLikeTracksSuboperationDTO(
                        1,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKTrackToLikeDTO(
                                        1,
                                        true,
                                        new VKSavedItemDTO(
                                                2,
                                                "title 2",
                                                "artist 2",
                                                2,
                                                180
                                        )
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        2,
                                        "title 2",
                                        "spotifyID 2",
                                        "lastFmID 2",
                                        "vkID 2",
                                        "vkOwnerID 2",
                                        Arrays.asList("foo", "bar"),
                                        120
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        3,
                                        "title 3",
                                        "spotifyID 3",
                                        "lastFmID 3",
                                        "vkID 3",
                                        "vkOwnerID 3",
                                        Arrays.asList("foo", "bar"),
                                        180
                                )
                        ))
                ),
                new AppUser()
        );
        addTracksOperation.setUser(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(addTracksOperation);

        this.mockMvc.perform(
                        post("/vk/saveOperation")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(requestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> this.mockMvc.perform(
                                        get("/vk/getOperation")
                                                .param("id", "8")
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk())
//                                .andExpect(content().json(requestJson))
                );
    }

    @Test
    public void testSaveOperation2() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        VKAddTracksOperationDTO addTracksOperation = new VKAddTracksOperationDTO(
                null,
                new Date(),
                new Date(),
                new VKSearchTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKSearchedTrackDTO(
                                        null,
                                        true,
                                        new SharedTrackDTO(
                                                null,
                                                "title 1",
                                                "spotifyID 1",
                                                "lastFmID 1",
                                                "vkID 1",
                                                "vkOwnerID 1",
                                                Arrays.asList("foo", "bar"),
                                                60
                                        ),
                                        new HashSet<>(List.of(
                                                new VKSavedItemDTO(
                                                        null,
                                                        "title 1",
                                                        "artist 1",
                                                        1,
                                                        60
                                                )
                                        ))
                                )
                        ))
                ),
                new VKLikeTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKTrackToLikeDTO(
                                        null,
                                        true,
                                        new VKSavedItemDTO(
                                                null,
                                                "title 2",
                                                "artist 2",
                                                2,
                                                180
                                        )
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        null,
                                        "title 2",
                                        "spotifyID 2",
                                        "lastFmID 2",
                                        "vkID 2",
                                        "vkOwnerID 2",
                                        Arrays.asList("foo", "bar"),
                                        120
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        null,
                                        "title 3",
                                        "spotifyID 3",
                                        "lastFmID 3",
                                        "vkID 3",
                                        "vkOwnerID 3",
                                        Arrays.asList("foo", "bar"),
                                        180
                                )
                        ))
                ),
                new AppUser()
        );
        addTracksOperation.setUser(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(addTracksOperation);

        this.mockMvc.perform(
                        post("/vk/saveOperation")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(requestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> {
                            MvcResult mvcResult = this.mockMvc.perform(
                                            get("/vk/getOperation")
                                                    .param("id", "1")
                                                    .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                    )
                                    .andExpect(status().isOk())
                                    .andReturn();

                        }
                );
    }

    @Test
    public void testGettingOperations() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        VKAddTracksOperationDTO sampleOperation = new VKAddTracksOperationDTO(
                null,
                new Date(),
                new Date(),
                new VKSearchTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKSearchedTrackDTO(
                                        null,
                                        true,
                                        new SharedTrackDTO(
                                                null,
                                                "title 1",
                                                "spotifyID 1",
                                                "lastFmID 1",
                                                "vkID 1",
                                                "vkOwnerID 1",
                                                Arrays.asList("foo", "bar"),
                                                60
                                        ),
                                        new HashSet<>(List.of(
                                                new VKSavedItemDTO(
                                                        null,
                                                        "title 1",
                                                        "artist 1",
                                                        1,
                                                        60
                                                )
                                        ))
                                )
                        ))
                ),
                new VKLikeTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<>(List.of(
                                new VKTrackToLikeDTO(
                                        null,
                                        true,
                                        new VKSavedItemDTO(
                                                null,
                                                "title 2",
                                                "artist 2",
                                                2,
                                                180
                                        )
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        null,
                                        "title 2",
                                        "spotifyID 2",
                                        "lastFmID 2",
                                        "vkID 2",
                                        "vkOwnerID 2",
                                        Arrays.asList("foo", "bar"),
                                        120
                                )
                        )),
                        new HashSet<>(List.of(
                                new SharedTrackDTO(
                                        null,
                                        "title 3",
                                        "spotifyID 3",
                                        "lastFmID 3",
                                        "vkID 3",
                                        "vkOwnerID 3",
                                        Arrays.asList("foo", "bar"),
                                        180
                                )
                        ))
                ),
                new AppUser()
        );
        sampleOperation.setUser(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String createOperationRequestJson = ow.writeValueAsString(sampleOperation);

        ResultHandler firstPageHandler = new ResultHandler() {
            @Override
            public void handle(MvcResult result) throws Exception {

                String content = result.getResponse().getContentAsString();
                VKAddTracksOperationsDTO page = mapper.readValue(content, VKAddTracksOperationsDTO.class);
                if (page.getOperations().size() > 0) {
                    throw new CustomException("fail", HttpStatus.CONFLICT);
                }
            }
        };

        this.mockMvc.perform(
                        post("/vk/saveOperation")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(createOperationRequestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> this.mockMvc.perform(
                                        post("/vk/saveOperation")
                                                .contentType(APPLICATION_JSON_UTF8)
                                                .content(createOperationRequestJson)
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk())
                )
                .andDo(
                        result -> this.mockMvc.perform(
                                        post("/vk/saveOperation")
                                                .contentType(APPLICATION_JSON_UTF8)
                                                .content(createOperationRequestJson)
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk())
                )
                .andDo(
                        result -> this.mockMvc.perform(
                                        post("/vk/saveOperation")
                                                .contentType(APPLICATION_JSON_UTF8)
                                                .content(createOperationRequestJson)
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk())
                )

                .andDo(
                        result -> {
                            MvcResult getOperationsResult = this.mockMvc.perform(
                                        get("/vk/getOperations")
                                                .param("page", "1")
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk()).andReturn();
                            String responseBody = getOperationsResult.getResponse().getContentAsString();
                            VKAddTracksOperationsDTO response = mapper.readValue(responseBody, VKAddTracksOperationsDTO.class);
                            Assert.isTrue(response.getTotal() > 0 );
                        }
//                                .andExpect(content().json(requestJson))
//                                .andExpect()
                );
    }

    @Test
    public void testGettingOperations2() throws Exception {
        TestUserDetails userDetails = userService.getTestUserDetails();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        this.mockMvc.perform(
                        get("/operations")
                                .param("page", "1")
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk());
    }
}
