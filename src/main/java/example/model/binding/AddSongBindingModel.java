package example.model.binding;

import example.model.entity.Genre;
import example.model.entity.SingerEntity;
import io.micrometer.core.lang.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddSongBindingModel {
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    private String imageUrl;
    @NotNull
    private String videoUrl;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @NotNull
    private Genre genre;
    @NotNull
    private String singerEntity;


    public String getName() {
        return name;
    }

    public AddSongBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddSongBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AddSongBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AddSongBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getSingerEntity() {
        return singerEntity;
    }

    public AddSongBindingModel setSingerEntity(String singerEntity) {
        this.singerEntity = singerEntity;
        return this;
    }
}
