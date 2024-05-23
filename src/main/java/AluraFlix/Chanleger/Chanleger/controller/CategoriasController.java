package AluraFlix.Chanleger.Chanleger.controller;

import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosAtualizacaoCategoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosCadastroCategoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosDetalhamentoCategoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.categoria.DadosListagemCategoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.CategoriaRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.service.VideoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    CategoriaRepository repository;
    @Autowired
    VideoService videoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder){
        var categoria = new Categoria(dados);
        repository.save(categoria);

        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @GetMapping
    public List<DadosListagemCategoria> listar(){
        return repository.findAll().stream().map(DadosListagemCategoria::new).toList();
    }

    @GetMapping("/{categoriaId}/videos")
    public ResponseEntity<List<Video>> buscarVideosPorCategoria(@PathVariable Long categoriaId) {
        List<Video> videos = videoService.buscarVideosPorCategoria(categoriaId);
        return ResponseEntity.ok(videos);
    }


    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var categoria = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody DadosAtualizacaoCategoria dados) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.atualizarInformacoes(dados);
            repository.save(categoria);
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build(); // retorna 404 se o ID não for encontrado
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // retorna 204 No Content após deletar
    }

}
