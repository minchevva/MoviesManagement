package pu.fmi.MoviesManagementSystem.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pu.fmi.MoviesManagementSystem.Entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
/*JpaRepository<Movie, Integer>: Това е специфичен интерфейс на Spring Data JPA, който предоставя готови методи за работа с базата данни. Първият параметър е типът на ентитито (Movie), а вторият е типът на основния ключ (Integer).
Автоматични CRUD операции:
save(): Запазва или актуализира ентити обект в базата данни.
findById(): Търси обект по неговия ID.
findAll(): Връща всички записи от базата данни.
deleteById(): Изтрива обект по ID.*/