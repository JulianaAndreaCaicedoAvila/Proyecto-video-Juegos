

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DesarrolloWEB_crud.demopostgres.dao.PrestamoDao;
import com.DesarrolloWEB_crud.demopostgres.entity.Prestamo;

import jakarta.transaction.Transactional;

@Service
public class ProcesoServiceImplement implements ProcesoService {
    @Autowired
    private ProcesoDao prestamoDao;

    @Override
    @Transactional
    public List<Prestamo> findAll() {
        return prestamoDao.findAll();
    }

    @Override
    @Transactional
    public Prestamo save(Prestamo prestamo) {
        return prestamoDao.save(prestamo);
    }

    @Override
    @Transactional
    public Prestamo findById(Long id) {
        return prestamoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Prestamo prestamo) {
        prestamoDao.delete(prestamo);
    }
}
