package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria;

import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;
public record DadosDetalhamentoCategoria(Long id, String titulo, String cor) {
    public DadosDetalhamentoCategoria (Categoria categoria){
        this(categoria.getId(),categoria.getTitulo(),categoria.getCor());
    }
}
