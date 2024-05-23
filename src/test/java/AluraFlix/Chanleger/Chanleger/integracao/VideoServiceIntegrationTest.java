package AluraFlix.Chanleger.Chanleger.integracao;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosCadastroVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.CategoriaRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.VideoRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.service.VideoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class VideoServiceIntegrationTest {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCriarVideoComCategoriaExistente() {
        // Dados de entrada
        DadosCadastroVideo dados = new DadosCadastroVideo(
                1L,
                "Teste Video",
                "Descricao do video teste",
                "http://test.url"
        );

        // Executar serviço
        Video video = videoService.criarVideo(dados);

        // Verificações
        assertNotNull(video);
        assertEquals("Teste Video", video.getTitulo());
        assertNotNull(video.getCategoria());
        assertEquals(1L, video.getCategoria().getId());

        // Verificar se o vídeo foi persistido
        Optional<Video> videoOptional = videoRepository.findById(video.getId());
        assertTrue(videoOptional.isPresent());
    }

    @Test
    public void testCriarVideoSemCategoria() {
        // Dados de entrada sem categoria
        DadosCadastroVideo dados = new DadosCadastroVideo(
                null,
                "Teste Video Sem Categoria",
                "Descricao do video teste sem categoria",
                "http://test.url/semcategoria"
        );

        // Executar serviço
        Video video = videoService.criarVideo(dados);

        // Verificações
        assertNotNull(video);
        assertEquals("Teste Video Sem Categoria", video.getTitulo());
        assertNotNull(video.getCategoria());
        assertEquals(1L, video.getCategoria().getId());

        // Verificar se o vídeo foi persistido
        Optional<Video> videoOptional = videoRepository.findById(video.getId());
        assertTrue(videoOptional.isPresent());
    }

    @Test
    public void testBuscarVideosPorCategoria() {
        // Pré-condição: Certificar que há vídeos na categoria 1
        Long categoriaId = 1L;

        List<Video> videos = videoService.buscarVideosPorCategoria(categoriaId);

        // Verificações
        assertNotNull(videos);
        assertFalse(videos.isEmpty());
    }
}