
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

import com.DesarrolloWEB_crud.demopostgres.entity.Prestamo;
import com.DesarrolloWEB_crud.demopostgres.services.PrestamoService;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<Object> getAllPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoService.findAll();
            return new ResponseEntity<>(prestamos, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prestamos/{id}")
    public ResponseEntity<Object> getPrestamoById(@PathVariable Long id) {
        try {
            Prestamo prestamo = prestamoService.findById(id);
            if (prestamo != null) {
                return new ResponseEntity<>(prestamo, HttpStatus.OK);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Prestamo not found with id: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/prestamos")
    public ResponseEntity<Object> createPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo createdPrestamo = prestamoService.save(prestamo);
            return new ResponseEntity<>(createdPrestamo, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/prestamos/{id}")
    public ResponseEntity<Object> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Map<String, Object> map = new HashMap<>();

        try {
            Prestamo currentPrestamo = prestamoService.findById(id);
            // Actualiza los campos necesarios del objeto prestamo
            // por ejemplo: currentPrestamo.setCampoNuevo(prestamo.getCampoNuevo());

            Prestamo updatedPrestamo = prestamoService.save(currentPrestamo);
            return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/prestamos/{id}")
    public ResponseEntity<Object> deletePrestamo(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        try {
            Prestamo currentPrestamo = prestamoService.findById(id);
            prestamoService.delete(currentPrestamo);
            map.put("deleted", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
