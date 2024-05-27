package AluraFlix.Chanleger.Chanleger.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("deveria devolver status code 200 para o get categoria")
    public void testeGetCategorias() throws Exception{
        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveria devolver status code 201 para o post categoria")
    public void testePOSTCategorias() throws Exception{
        String newCategoriaJson = "{ \"titulo\": \"new categoria\", \"cor\": \"cor\" }";

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCategoriaJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("deveria devolver status code 200 para o put")
    public void testPutcategoria() throws Exception {
        String newCategoriaJson = "{ \"titulo\": \"new categoria\", \"cor\": \"cor\" }";

        MvcResult result = mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategoriaJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        String id = location.split("/")[location.split("/").length - 1];


        String updateCategoriaJson = "{ \"titulo\": \"update categoria\", \"cor\": \"update cor\" }";

        mockMvc.perform(put("/categorias/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCategoriaJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveria devolver status code 204 para o delete")
    public void testDeleteCategoria() throws Exception {
        String newCategoriaJson = "{ \"titulo\": \"update categoria\", \"cor\": \"update cor\" }";

        MvcResult result = mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategoriaJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assert location != null;
        String id = location.split("/")[location.split("/").length - 1];

        mockMvc.perform(delete("/categorias/" + id))
                .andExpect(status().isNoContent());
    }

}
