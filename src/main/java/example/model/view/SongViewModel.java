package example.model.view;

import com.google.gson.annotations.Expose;
import example.model.entity.Genre;
import example.model.entity.SingerEntity;
import example.model.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDate;

public class SongViewModel {

    private String name;
    private String imageUrl;
    private String videoUrl;
    private Instant releaseDate;
    private Genre genre;
    private String singerEntity;
    private String userEntity;

    public String getName() {
        return name;
    }

    public SongViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SongViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public SongViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public SongViewModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public SongViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getSingerEntity() {
        return singerEntity;
    }

    public SongViewModel setSingerEntity(String singerEntity) {
        this.singerEntity = singerEntity;
        return this;
    }

    public String getUserEntity() {
        return userEntity;
    }

    public SongViewModel setUserEntity(String userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
