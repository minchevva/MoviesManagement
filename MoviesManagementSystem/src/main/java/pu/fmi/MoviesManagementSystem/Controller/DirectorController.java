package pu.fmi.MoviesManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pu.fmi.MoviesManagementSystem.DTO.DirectorDTO;
import pu.fmi.MoviesManagementSystem.Entity.Director;
import pu.fmi.MoviesManagementSystem.Repository.DirectorRepository;
import pu.fmi.MoviesManagementSystem.Service.DirectorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors() {
        List<Director> directors = directorRepository.findAll();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable int id) {
        return directorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Director> addDirector(@RequestBody DirectorDTO directorDTO) {
        Director director = new Director();
        director.setFirstName(directorDTO.getFirstName());
        director.setLastName(directorDTO.getLastName());
        director.setBirthDate(directorDTO.getBirthDate());

        Director savedDirector = directorRepository.save(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable int id, @RequestBody Director directorDetails) {
        return directorRepository.findById(id)
                .map(director -> {
                    director.setFirstName(directorDetails.getFirstName());
                    director.setLastName(directorDetails.getLastName());
                    director.setBirthDate(directorDetails.getBirthDate());
                    Director updatedDirector = directorRepository.save(director);
                    return ResponseEntity.ok(updatedDirector);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable int id) {
        directorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
