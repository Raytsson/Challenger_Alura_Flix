package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
        @NotNull
        Long id,
        String titulo,
        String cor
) {
}
