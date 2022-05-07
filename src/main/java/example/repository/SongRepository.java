package example.repository;

import example.model.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {


    Optional<SongEntity> findByName(String name);
}
