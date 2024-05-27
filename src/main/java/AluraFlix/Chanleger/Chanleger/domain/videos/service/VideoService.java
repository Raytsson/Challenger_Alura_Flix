package AluraFlix.Chanleger.Chanleger.domain.videos.service;


import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosCadastroVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.CategoriaRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.VideoRepository;
import AluraFlix.Chanleger.Chanleger.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        this.videoRepository = videoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void validarTituloEUrlExistente(String titulo, String url) {
        boolean tituloExistente = videoRepository.existsByTitulo(titulo);
        boolean urlExistente = videoRepository.existsByUrl(url);

        if (tituloExistente && urlExistente) {
            throw new ValidacaoException("Título e URL já existentes");
        } else if (tituloExistente) {
            throw new ValidacaoException("Título já existente");
        } else if (urlExistente) {
            throw new ValidacaoException("URL já existente");
        }
    }

    public Video criarVideo(DadosCadastroVideo dados) {
        // Verificar se foi especificada uma categoria
        Long idCategoria = dados.idCategoria();
        if (idCategoria == null) {
            idCategoria = 1L;
        }

        // Verificar se a categoria especificada é válida
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (categoriaOptional.isEmpty()) {
            throw new IllegalArgumentException("Categoria não encontrada para o ID fornecido: " + idCategoria);
        }

        Categoria categoria = categoriaOptional.get();

        Video video = new Video(dados, categoria);

        return videoRepository.save(video);
    }

    public List<Video> buscarVideosPorCategoria(Long categoriaId) {
        return videoRepository.findByCategoriaId(categoriaId);
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }
}