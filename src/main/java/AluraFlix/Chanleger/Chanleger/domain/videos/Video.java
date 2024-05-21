package AluraFlix.Chanleger.Chanleger.domain.videos;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosAtualizacaoVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosCadastroVideo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Table(name = "videos")
@Entity(name = "Video")
@Getter
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

    public Video(DadosCadastroVideo dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
    }

    public void atualizarInformacoes(DadosAtualizacaoVideo dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.url() != null){
            this.url = dados.url();
        }
    }
}
