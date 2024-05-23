package AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria;

import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;

public record DadosListagemCategoria(Long id, String titulo, String cor) {
    public DadosListagemCategoria (Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
