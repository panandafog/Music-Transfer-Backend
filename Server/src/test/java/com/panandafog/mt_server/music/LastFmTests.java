package com.panandafog.mt_server.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.TestUserDetails;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.dummy.Dummy;
import com.panandafog.mt_server.music.DTO.last_fm.*;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class LastFmTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveOperation() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        LastFmAddTracksOperationDTO addTracksOperation = new LastFmAddTracksOperationDTO(
                1,
                new Date(),
                new Date(),
                new LastFmSearchTracksSuboperationDTO(
                        1,
                        new Date(),
                        new Date(),
                        new HashSet<LastFmSearchedTrackDTO>(Arrays.asList(
                                new LastFmSearchedTrackDTO(
                                        1,
                                        true,
                                        new HashSet<LastFmTrackDTO>(List.of(
                                                new LastFmTrackDTO(
                                                        1,
                                                        "1",
                                                        "name 1",
                                                        "artist 1",
                                                        "url 1"
                                                )
                                        ))
                                ),
                                new LastFmSearchedTrackDTO(
                                        2,
                                        true,
                                        new HashSet<LastFmTrackDTO>(List.of(
                                                new LastFmTrackDTO(
                                                        2,
                                                        "2",
                                                        "name 2",
                                                        "artist 2",
                                                        "url 2"
                                                )
                                        ))
                                )
                        ))

                ),
                new LastFmLikeTracksSuboperationDTO(
                        1,
                        new Date(),
                        new Date(),
                        new HashSet<LastFmTrackToLikeDTO>(List.of(
                                new LastFmTrackToLikeDTO(
                                        1,
                                        true,
                                        new LastFmTrackDTO(
                                                3,
                                                "3",
                                                "name 3",
                                                "artist 3",
                                                "url 3"
                                        )
                                )
                        )),
                        new HashSet<SharedTrackDTO>(List.of(
                                new SharedTrackDTO(
                                        1,
                                        "title 1",
                                        "spotifyID 1",
                                        "lastFmID 1",
                                        "vkID 1",
                                        "vkOwnerID 1",
                                        Arrays.asList("foo", "bar"),
                                        60
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
                        post("/lastfm/saveOperation")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(requestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> this.mockMvc.perform(
                                        get("/lastfm/getOperation")
                                                .param("id", "1")
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                )
                                .andExpect(status().isOk())
                                .andExpect(content().json(requestJson))
                );
    }

    @Test
    public void testSaveOperation2() throws Exception {

        TestUserDetails userDetails = userService.getTestUserDetails();

        LastFmAddTracksOperationDTO addTracksOperation = new LastFmAddTracksOperationDTO(
                null,
                new Date(),
                new Date(),
                new LastFmSearchTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<LastFmSearchedTrackDTO>(Arrays.asList(
                                new LastFmSearchedTrackDTO(
                                        null,
                                        true,
                                        new HashSet<LastFmTrackDTO>(List.of(
                                                new LastFmTrackDTO(
                                                        null,
                                                        "1",
                                                        "name 1",
                                                        "artist 1",
                                                        "url 1"
                                                )
                                        ))
                                ),
                                new LastFmSearchedTrackDTO(
                                        null,
                                        true,
                                        new HashSet<LastFmTrackDTO>(List.of(
                                                new LastFmTrackDTO(
                                                        null,
                                                        "2",
                                                        "name 2",
                                                        "artist 2",
                                                        "url 2"
                                                )
                                        ))
                                )
                        ))

                ),
                new LastFmLikeTracksSuboperationDTO(
                        null,
                        new Date(),
                        new Date(),
                        new HashSet<LastFmTrackToLikeDTO>(List.of(
                                new LastFmTrackToLikeDTO(
                                        null,
                                        true,
                                        new LastFmTrackDTO(
                                                null,
                                                "3",
                                                "name 3",
                                                "artist 3",
                                                "url 3"
                                        )
                                )
                        )),
                        new HashSet<SharedTrackDTO>(List.of(
                                new SharedTrackDTO(
                                        null,
                                        "title 1",
                                        "spotifyID 1",
                                        "lastFmID 1",
                                        "vkID 1",
                                        "vkOwnerID 1",
                                        Arrays.asList("foo", "bar"),
                                        60
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
                        post("/lastfm/saveOperation")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(requestJson)
                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                )
                .andExpect(status().isOk())
                .andDo(
                        result -> {
                            MvcResult mvcResult = this.mockMvc.perform(
                                        get("/lastfm/getOperation")
                                                .param("id", "1")
                                                .header(userDetails.getHeaderKey(), userDetails.getHeaderValue())
                                        )
                                    .andExpect(status().isOk())
                                    .andExpect(content().json(requestJson))
                                    .andReturn();

                        }
                );
    }
}

