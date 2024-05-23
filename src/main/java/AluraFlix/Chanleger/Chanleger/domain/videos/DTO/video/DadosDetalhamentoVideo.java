package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video;

import AluraFlix.Chanleger.Chanleger.domain.videos.Video;

public record DadosDetalhamentoVideo(Long id, Long idCategoria, String titulo, String descricao, String url) {
    public DadosDetalhamentoVideo (Video video){
        this(video.getId(), video.getCategoria().getId(), video.getTitulo(), video.getDescricao(), video.getUrl());
    }
}
