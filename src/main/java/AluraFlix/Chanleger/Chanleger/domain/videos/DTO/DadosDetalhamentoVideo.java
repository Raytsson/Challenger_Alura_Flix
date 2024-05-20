package AluraFlix.Chanleger.Chanleger.domain.videos.DTO;

import AluraFlix.Chanleger.Chanleger.domain.videos.Video;

public record DadosDetalhamentoVideo(Long id, String titulo, String descricao, String url) {
    public DadosDetalhamentoVideo (Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl());
    }
}
