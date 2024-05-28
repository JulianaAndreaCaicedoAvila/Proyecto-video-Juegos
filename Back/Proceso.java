
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
@RequestMapping("/api/v1/proceso")
public class Proceso {

    @Autowired
    private procesoService procesoervice;

    @GetMapping
    public ResponseEntity<Object> getAllproceso() {
        try {
            List<proceso> proceso = procesoService.findAll();
            return new ResponseEntity<>(proceso, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/proceso/{id}")
    public ResponseEntity<Object> getprocesoById(@PathVariable Long id) {
        try {
            proceso proceso = procesoService.findById(id);
            if (proceso != null) {
                return new ResponseEntity<>(proceso, HttpStatus.OK);
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

    @PostMapping("/proceso")
    public ResponseEntity<Object> createproceso(@RequestBody Prestamo prestamo) {
        try {
            proceso createdproceso = procesoService.save(prestamo);
            return new ResponseEntity<>(createdproceso, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/proceso/{id}")
    public ResponseEntity<Object> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Map<String, Object> map = new HashMap<>();

        try {
            Prestamo currentproceso = proceso.findById(id);
            // Actualiza los campos necesarios del objeto prestamo
            // por ejemplo: currentPrestamo.setCampoNuevo(prestamo.getCampoNuevo());

            Prestamo proceso = proceso.save(currentPrestamo);
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
