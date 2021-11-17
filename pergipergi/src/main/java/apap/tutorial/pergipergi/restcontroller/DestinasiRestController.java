package apap.tutorial.pergipergi.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.service.DestinasiService;
import apap.tutorial.pergipergi.service.DestinasiRestService;


@RestController
@RequestMapping("/api/v1")
public class DestinasiRestController {
    
    @Autowired
    private DestinasiRestService destinasiRestService;

    
    @PostMapping(value = "/destinasi")
    private ResponseEntity createDestinasi(@Valid @RequestBody DestinasiModel destinasi, BindingResult bindingresult) {

        if (bindingresult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            destinasiRestService.createDestinasi(destinasi);

            return ResponseEntity.ok("Create Destinasi success");
        }

    }

    @GetMapping(value="/destinasi/{noDestinasi}")
    private DestinasiModel retrieveDestinasi(@PathVariable("noDestinasi") Long noDestinasi) {

        try {
            return destinasiRestService.getDestByNoDest(noDestinasi);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No destinasi " + String.valueOf(noDestinasi) + " not found.");
        }

    }

    @DeleteMapping(value="/destinasi/{noDestinasi}")
    private ResponseEntity deleteDestinasi (@PathVariable("noDestinasi") Long noDestinasi) {

        try {
            destinasiRestService.deleteDestinasi(noDestinasi);
            return ResponseEntity.ok("Destinasi " + noDestinasi + " has been deleted.");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No destinasi " + String.valueOf(noDestinasi) + " not found.");
        // } catch (UnsupportedOperationException e) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel agensi is still open or has tour guide!");
        }

    }

    @PutMapping(value="/destinasi/{noDestinasi}")
    private ResponseEntity updateDestinasi(@PathVariable("noDestinasi") Long noDestinasi, @RequestBody DestinasiModel destinasi) {

        try {
            destinasiRestService.updateDestinasi(noDestinasi, destinasi);

            return ResponseEntity.ok("Update Destinasi success");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No destinasi " + String.valueOf(noDestinasi) + " not found.");
        }

    }
    
    @GetMapping(value="/list-destinasi")
    private List<DestinasiModel> retrieveListDestinasi() {
        return destinasiRestService.retrieveListDestinasi();
    }
}
