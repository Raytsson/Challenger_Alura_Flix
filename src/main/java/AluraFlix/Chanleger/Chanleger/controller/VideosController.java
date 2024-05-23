package AluraFlix.Chanleger.Chanleger.controller;

import AluraFlix.Chanleger.Chanleger.domain.videos.Categoria;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosAtualizacaoVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosCadastroVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosDetalhamentoVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.video.DadosListagemVideo;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.CategoriaRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.VideoRepository;
import AluraFlix.Chanleger.Chanleger.domain.videos.service.VideoService;
import AluraFlix.Chanleger.Chanleger.exceptions.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("videos")
public class VideosController {

    @Autowired
    VideoRepository repository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    VideoService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroVideo dados, UriComponentsBuilder uriBuilder) {
        try {
            // Realizar a validação
            service.validarTituloEUrlExistente(dados.titulo(), dados.url());

            // Se a validação passar, salvar o vídeo
            Video video = service.criarVideo(dados);

            // Criar URI para o recurso criado
            URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosDetalhamentoVideo(video));
        } catch (ValidacaoException ex) {
            // Se a validação falhar, retornar uma resposta de erro
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping
    public List<DadosListagemVideo> listar(){
        return repository.findAll().stream().map(DadosListagemVideo::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscaPorId(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVideo(@PathVariable Long id, @RequestBody DadosAtualizacaoVideo dados) {
        Optional<Video> videoOptional = repository.findById(id);
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();

            // Realizar a validação
            try {
                service.validarTituloEUrlExistente(dados.titulo(), dados.url());
            } catch (ValidacaoException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }

            // Se a validação passar, atualizar o vídeo
            video.atualizarInformacoes(dados);
            repository.save(video);
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
       if (!repository.existsById(id)) {
           return ResponseEntity.notFound().build(); // Retorna 404 se a entidade não existir
       }
       repository.deleteById(id);
       return ResponseEntity.noContent().build();
   }
}
