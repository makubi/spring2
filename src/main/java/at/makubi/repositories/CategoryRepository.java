package at.makubi.repositories;

import at.makubi.entities.Category;
import at.makubi.entities.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);
}
