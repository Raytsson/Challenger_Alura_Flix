package AluraFlix.Chanleger.Chanleger.domain.videos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public record DadosCadastroVideo(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        @Pattern(regexp = "^(http|https)://.*$")
        @URL
        String url
        ) {
}
