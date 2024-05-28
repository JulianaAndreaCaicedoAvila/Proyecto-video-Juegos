

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DesarrolloWEB_crud.demopostgres.dao.PrestamoDao;
import com.DesarrolloWEB_crud.demopostgres.entity.Prestamo;

import jakarta.transaction.Transactional;

@Service
public class NivelesServiceImplement implements NivelesService {
    @Autowired
    private NivelesDao NivelesDao;

    @Override
    @Transactional
    public List<Niveles> findAll() {
        return prestamoDao.findAll();
    }

    @Override
    @Transactional
    public Niveles save(Niveles Niveles) {
        return NivelesDao.save(Niveles);
    }

    @Override
    @Transactional
    public Niveles findById(Long id) {
        return NivelesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Niveles Niveles) {
        Niveles.delete(Niveles);
    }
}
