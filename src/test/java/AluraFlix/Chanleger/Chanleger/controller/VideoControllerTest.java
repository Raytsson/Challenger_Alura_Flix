package AluraFlix.Chanleger.Chanleger.controller;

import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class VideoControllerTest {

    @MockBean
    private VideoService videoService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("deveria devolver status code 200 para o get")
    public void testGetVideos() throws Exception {
        mockMvc.perform(get("/videos"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveria devolver status code 201 para o post")
    public void testePostVideos() throws Exception {
        doReturn(new Video().setCategoria(new Categoria())).when(videoService).criarVideo(any());

        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVideoJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /videos/:id deve atualizar um vídeo e retornar 200")
    public void testPutVideo() throws Exception {
        doReturn(new Video().setCategoria(new Categoria())).when(videoService).criarVideo(any());
        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        MvcResult result = mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVideoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        String id = location.split("/")[location.split("/").length - 1];

        String updateCategoriaJson = "{ \"titulo\": \"update categoria\"}";

        mockMvc.perform(put("/videos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCategoriaJson))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("DELETE /videos/:id deve deletar um vídeo e retornar 204")
    public void testDeleteVideo() throws Exception {
        doReturn(new Video().setCategoria(new Categoria())).when(videoService).criarVideo(any());
        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        String id = createVideoAndGetId(newVideoJson);

        mockMvc.perform(delete("/videos/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/videos/" + id))
                .andExpect(status().isNotFound());
    }

    private String createVideoAndGetId(String videoJson) throws Exception {
        MvcResult result = mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(videoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        return location.split("/")[location.split("/").length - 1];
    }
}



