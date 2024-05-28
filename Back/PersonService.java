

import java.util.List;

import com.DesarrolloWEB_crud.demopostgres.entity.Person;

public interface PersonService {
	
	public List<Person> findAll();
	
	public Person save(Person person);
		
	public Person findById(Long id);
	
	public void delete(Person person);


}
