package AluraFlix.Chanleger.Chanleger.controller;

import AluraFlix.Chanleger.Chanleger.domain.videos.DTO.*;
import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import AluraFlix.Chanleger.Chanleger.domain.videos.repositorie.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("videos")
public class VideosController {

    @Autowired
    VideoRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVideo dados, UriComponentsBuilder uriBuilder){
        var video =  new Video(dados);
            repository.save(video);
        var uri = uriBuilder.path("videos/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVideo(video));
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
    public ResponseEntity<Video> atualizarVideo(@PathVariable Long id, @RequestBody DadosAtualizacaoVideo dados) {
        Optional<Video> videoOptional = repository.findById(id);
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
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
