package pu.fmi.MoviesManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pu.fmi.MoviesManagementSystem.DTO.ActorDTO;
import pu.fmi.MoviesManagementSystem.Entity.Actor;
import pu.fmi.MoviesManagementSystem.Repository.ActorRepository;
import pu.fmi.MoviesManagementSystem.Service.ActorService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable int id) {
        return actorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setFirstName(actorDTO.getFirstName());
        actor.setLastName(actorDTO.getLastName());
        actor.setBirthDate(actorDTO.getBirthDate());

        Actor savedActor = actorRepository.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor actorDetails) {
        return actorRepository.findById(id)
                .map(actor -> {
                    actor.setFirstName(actorDetails.getFirstName());
                    actor.setLastName(actorDetails.getLastName());
                    actor.setBirthDate(actorDetails.getBirthDate());
                    Actor updatedActor = actorRepository.save(actor);
                    return ResponseEntity.ok(updatedActor);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {
        actorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
