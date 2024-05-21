package AluraFlix.Chanleger.Chanleger.domain.videos;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosCadastroCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Categoria(DadosCadastroCategoria dados) {
        this.titulo = dados.titulo();
        this.cor = dados.cor();
    }
}
