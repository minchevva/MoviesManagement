package pu.fmi.MoviesManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.fmi.MoviesManagementSystem.Entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
