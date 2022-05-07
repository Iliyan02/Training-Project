package example.repository;

import example.model.entity.SingerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SingerRepository extends JpaRepository<SingerEntity, Long> {

    @Query("SELECT s.name FROM SingerEntity s")
    List<String> findAllSingers();

    Optional<SingerEntity> findByName(String singerEntity);

    Optional<Object> findSingerEntityByName(String name);
}
