
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DesarrolloWEB_crud.demopostgres.entity.Empleado_operaciones;
import com.DesarrolloWEB_crud.demopostgres.services.Empleado_operacionesService;

@RestController
@RequestMapping("/api/v1/Niveles")
public class Niveles {

    @Autowired
    private Empleado_operacionesService empleado_operacionesService;

    @GetMapping
    public ResponseEntity<Object> getAllNiveles() {
        List<Niveles> empleadoOperaciones = NivelesService.findAll();
        return new ResponseEntity<>(Niveles, HttpStatus.OK);
    }

    @GetMapping("/Niveles-Niveles/{id}")
    public ResponseEntity<Object> getNivelesById(@PathVariable Long id) {
        try {
            Niveles Niveles = Niveles.findById(id);
            return new ResponseEntity<>(Niveles, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping (value="/empleado-operaciones")
    public ResponseEntity<Object> createEmpleadoOperaciones(@RequestBody Niveles Niveles) {
        Niveles savedEmpleadoOperaciones = Niveles.save(Niveles);
        return new ResponseEntity<>(Niveles, HttpStatus.CREATED);
    }

    @PutMapping("/Niveles-Niveles/{id}")
    public ResponseEntity<Object> updateEmpleadoOperaciones(@PathVariable Long id, @RequestBody Empleado_operaciones empleadoOperaciones) {
        Map<String, Object> map = new HashMap<>();

        try {
            Niveles currentNiveles = NivelesService.findById(id);
            if (currentNiveles != null) {
                currentNiveles.setNombre(Niveles.getNombre());

                Niveles updatedNiveles = Niveles.save(currentNiveles);
                return new ResponseEntity<>(updatedNiveles, HttpStatus.OK);
            } else {
                map.put("message", "Empleado operaciones not found with id: " + id);
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/Niveles-Niveles/{id}")
    public ResponseEntity<Object> deleteNiveles(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        try {
            Niveles currentNiveles= NivelesService.findById(id);
            if (currentNiveles!= null) {
            	Niveles.delete(Niveles);
                map.put("deleted", true);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                map.put("message", "Empleado operaciones not found with id: " + id);
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
