package pu.fmi.MoviesManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.fmi.MoviesManagementSystem.Entity.Director;
import pu.fmi.MoviesManagementSystem.Repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Optional<Director> getDirectorById(int id) {
        return directorRepository.findById(id);
    }

    public Director addDirector(Director director) {
        return directorRepository.save(director);
    }

    public Director updateDirector(int id, Director updatedDirector) {
        return directorRepository.findById(id).map(director -> {
            director.setFirstName(updatedDirector.getFirstName());
            director.setLastName(updatedDirector.getLastName());
            director.setBirthDate(updatedDirector.getBirthDate());
            return directorRepository.save(director);
        }).orElseThrow(() -> new RuntimeException("Director not found"));
    }

    public void deleteDirector(int id) {
        directorRepository.deleteById(id);
    }
}
