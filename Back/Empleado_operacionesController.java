
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
@RequestMapping("/api/v1/empleado-operaciones")
public class Empleado_operacionesController {

    @Autowired
    private Empleado_operacionesService empleado_operacionesService;

    @GetMapping
    public ResponseEntity<Object> getAllEmpleadoOperaciones() {
        List<Empleado_operaciones> empleadoOperaciones = empleado_operacionesService.findAll();
        return new ResponseEntity<>(empleadoOperaciones, HttpStatus.OK);
    }

    @GetMapping("/empleado-operacione/{id}")
    public ResponseEntity<Object> getEmpleadoOperacionesById(@PathVariable Long id) {
        try {
            Empleado_operaciones empleadoOperaciones = empleado_operacionesService.findById(id);
            return new ResponseEntity<>(empleadoOperaciones, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping (value="/empleado-operaciones")
    public ResponseEntity<Object> createEmpleadoOperaciones(@RequestBody Empleado_operaciones empleadoOperaciones) {
        Empleado_operaciones savedEmpleadoOperaciones = empleado_operacionesService.save(empleadoOperaciones);
        return new ResponseEntity<>(savedEmpleadoOperaciones, HttpStatus.CREATED);
    }

    @PutMapping("/empleado-operacione/{id}")
    public ResponseEntity<Object> updateEmpleadoOperaciones(@PathVariable Long id, @RequestBody Empleado_operaciones empleadoOperaciones) {
        Map<String, Object> map = new HashMap<>();

        try {
            Empleado_operaciones currentEmpleadoOperaciones = empleado_operacionesService.findById(id);
            if (currentEmpleadoOperaciones != null) {
                currentEmpleadoOperaciones.setNombre(empleadoOperaciones.getNombre());

                Empleado_operaciones updatedEmpleadoOperaciones = empleado_operacionesService.save(currentEmpleadoOperaciones);
                return new ResponseEntity<>(updatedEmpleadoOperaciones, HttpStatus.OK);
            } else {
                map.put("message", "Empleado operaciones not found with id: " + id);
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/empleado-operaciones/{id}")
    public ResponseEntity<Object> deleteEmpleadoOperaciones(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        try {
            Empleado_operaciones currentEmpleadoOperaciones = empleado_operacionesService.findById(id);
            if (currentEmpleadoOperaciones != null) {
            	empleado_operacionesService.delete(currentEmpleadoOperaciones);
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
