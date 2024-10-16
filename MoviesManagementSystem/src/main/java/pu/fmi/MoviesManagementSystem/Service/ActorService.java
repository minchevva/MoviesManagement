package pu.fmi.MoviesManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.fmi.MoviesManagementSystem.Entity.Actor;
import pu.fmi.MoviesManagementSystem.Repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(int id) {
        return actorRepository.findById(id);
    }

    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor updateActor(int id, Actor updatedActor) {
        return actorRepository.findById(id).map(actor -> {
            actor.setFirstName(updatedActor.getFirstName());
            actor.setLastName(updatedActor.getLastName());
            actor.setBirthDate(updatedActor.getBirthDate());
            return actorRepository.save(actor);
        }).orElseThrow(() -> new RuntimeException("Actor not found"));
    }

    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }
}
