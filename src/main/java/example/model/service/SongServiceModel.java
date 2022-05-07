package example.model.service;

import example.model.entity.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import javax.xml.transform.sax.SAXResult;
import java.time.LocalDate;

public class SongServiceModel {

    private String name;
    private String imageUrl;
    private String videoUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Genre genre;
    private String singerEntity;
    private String user;

    public String getUser() {
        return user;
    }

    public SongServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getName() {
        return name;
    }

    public SongServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SongServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public SongServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public SongServiceModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getSingerEntity() {
        return singerEntity;
    }

    public SongServiceModel setSingerEntity(String singerEntity) {
        this.singerEntity = singerEntity;
        return this;
    }
}
