package example.model.entity;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class SongEntity extends BaseEntity {
    @Expose
    @Column(nullable = false)
    private String name;
    @Expose
    @Column(name = "image_url" ,nullable = false)
    private String imageUrl;
    @Expose
    @Column(name = "video_url",nullable = false)
    private String videoUrl;
    @Expose
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Expose
    @ManyToOne
    private SingerEntity singerEntity;
    @ManyToOne
    private UserEntity userEntity;

    public String getName() {
        return name;
    }

    public SongEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SongEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public SongEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public SongEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public SingerEntity getSingerEntity() {
        return singerEntity;
    }

    public SongEntity setSingerEntity(SingerEntity singerEntity) {
        this.singerEntity = singerEntity;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public SongEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
