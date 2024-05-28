

import java.util.List;

import com.DesarrolloWEB_crud.demopostgres.entity.Prestamo;

public interface ProcesoService {
    
    public List<Prestamo> findAll();
    
    public Prestamo save(Prestamo prestamo);
    
    public Prestamo findById(Long id);
    
    public void delete(Prestamo prestamo);
}
