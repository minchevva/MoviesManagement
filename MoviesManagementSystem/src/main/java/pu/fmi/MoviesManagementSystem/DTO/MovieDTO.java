package pu.fmi.MoviesManagementSystem.DTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MovieDTO {
    private String title;
    private LocalDate releaseDate;
    private int genreId;
    private int directorId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }
}
