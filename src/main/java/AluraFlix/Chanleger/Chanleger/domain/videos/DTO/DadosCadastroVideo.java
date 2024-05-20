package AluraFlix.Chanleger.Chanleger.domain.videos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroVideo(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        @Pattern(regexp = "^(http|https)://.*$")
        String url
        ) {
}
