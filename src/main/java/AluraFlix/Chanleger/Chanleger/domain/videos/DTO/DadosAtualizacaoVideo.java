package AluraFlix.Chanleger.Chanleger.domain.videos.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        String url
) {
}
