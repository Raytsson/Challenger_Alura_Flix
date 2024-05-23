package AluraFlix.Chanleger.Chanleger.domain.videos.repositorie;

import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByUrl(String url);

    List<Video> findByCategoriaId(Long categoriaId);
}
