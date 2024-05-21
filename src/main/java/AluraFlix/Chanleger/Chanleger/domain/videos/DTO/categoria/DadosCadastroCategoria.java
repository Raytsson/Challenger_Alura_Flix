package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(
        @NotBlank
        String titulo,
        @NotBlank
        String cor
) {
}
