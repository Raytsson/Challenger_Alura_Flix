package AluraFlix.Chanleger.Chanleger.domain.videos;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosAtualizacaoVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosCadastroVideo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Table(name = "videos")
@Entity(name = "Video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @URL
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora propriedades específicas do proxy Hibernate
    private Categoria categoria;

    public Video(DadosCadastroVideo dados, Categoria categoria) {
        this.categoria = categoria;
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
    }



    public void atualizarInformacoes(DadosAtualizacaoVideo dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.url() != null) {
            this.url = dados.url();
        }
    }

    public Video setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }
}
