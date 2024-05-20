package AluraFlix.Chanleger.Chanleger.domain.videos.repositorie;

import AluraFlix.Chanleger.Chanleger.domain.videos.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
