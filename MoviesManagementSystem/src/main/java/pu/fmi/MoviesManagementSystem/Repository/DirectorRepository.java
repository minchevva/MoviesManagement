package pu.fmi.MoviesManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.fmi.MoviesManagementSystem.Entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
