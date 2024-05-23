package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id,
        Long idCategoria,
        String titulo,
        String descricao,
        String url
) {
}
