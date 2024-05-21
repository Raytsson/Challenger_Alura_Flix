package AluraFlix.Chanleger.Chanleger.domain.videos.service;


import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.VideoRepository;
import AluraFlix.Chanleger.Chanleger.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

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
}
