package AluraFlix.Chanleger.Chanleger.domain.videos;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosAtualizacaoCategoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosCadastroCategoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "categorias")
@Entity(name = "Categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String cor;
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Video> video;

    public Categoria(DadosCadastroCategoria dados) {
        this.titulo = dados.titulo();
        this.cor = dados.cor();
    }

    public void atualizarInformacoes(DadosAtualizacaoCategoria dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.cor() != null){
            this.cor = dados.cor();
        }
    }
}
