

import java.util.List;


public interface NivelesService {
    
    public List<Niveles> findAll();
    
    public Niveles save(Niveles prestamo);
    
    public Niveles findById(Long id);
    
    public void delete(Niveles prestamo);
}
