package AluraFlix.Chanleger.Chanleger.controller;

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
    public void testPostVideo() throws Exception {
        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVideoJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("deveria devolver status code 200 para o put")
    public void testPutVideo() throws Exception {
        // Primeiro, crie um vídeo para atualizar
        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        MvcResult result = mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVideoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        String id = location.split("/")[location.split("/").length - 1];

        // Atualize o vídeo criado
        String updateVideoJson = "{ \"titulo\": \"Updated Video\", \"descricao\": \"Updated Description\", \"url\": \"http://updatedvideo.com\" }";

        mockMvc.perform(put("/videos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateVideoJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveria devolver status code 204 para o delete")
    public void testDeleteVideo() throws Exception {
        // Primeiro, crie um vídeo para deletar
        String newVideoJson = "{ \"titulo\": \"New Video\", \"descricao\": \"Description\", \"url\": \"http://newvideo.com\" }";

        MvcResult result = mockMvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVideoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        String id = location.split("/")[location.split("/").length - 1];

        // Delete o vídeo criado
        mockMvc.perform(delete("/videos/" + id))
                .andExpect(status().isNoContent());
    }





























}
