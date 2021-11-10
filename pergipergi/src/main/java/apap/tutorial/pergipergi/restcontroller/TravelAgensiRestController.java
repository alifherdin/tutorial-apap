package apap.tutorial.pergipergi.restcontroller;


import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;
import apap.tutorial.pergipergi.service.TravelAgensiRestService;
import apap.tutorial.pergipergi.service.TravelAgensiService;

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


@RestController
@RequestMapping("/api/v1")
public class TravelAgensiRestController {

    @Autowired
    private TravelAgensiRestService travelAgensiRestService;


    @PostMapping(value = "/agensi")
    private TravelAgensiModel createTravelAgensi(@Valid @RequestBody TravelAgensiModel agensi, BindingResult bindingresult) {

        if (bindingresult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return travelAgensiRestService.createAgensi(agensi);
        }

    }

    @GetMapping(value="agensi/{noAgensi}")
    private TravelAgensiModel retrieveAgensi(@PathVariable("noAgensi") Long noAgensi) {

        try {
            return travelAgensiRestService.getAgensiByNoAgensi(noAgensi);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No agensi " + String.valueOf(noAgensi) + " not found.");
        }

    }

    @DeleteMapping(value="agensi/{noAgensi}")
    private ResponseEntity deleteAgensi (@PathVariable("noAgensi") Long noAgensi) {

        try {
            travelAgensiRestService.deleteAgensi(noAgensi);
            return ResponseEntity.ok("Travel agensi with no agensi " + noAgensi + " deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No agensi " + String.valueOf(noAgensi) + " not found.");
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel agensi is still open or has tour guide!");
        }

    }

    @PutMapping(value="agensi/{noAgensi}")
    private TravelAgensiModel update(@PathVariable("noAgensi") Long noAgensi, @RequestBody TravelAgensiModel agensi) {

        try {
            return travelAgensiRestService.updateAgensi(noAgensi, agensi);
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No agensi " + String.valueOf(noAgensi) + " not found.");
        }

    }

    @GetMapping(value="/list-agensi")
    private List<TravelAgensiModel> retrieveListTravelAgensi() {
        return travelAgensiRestService.retrieveListAgensi();
    }

    @GetMapping(value = "agensi/{noAgensi}/status")
    private Mono<String> getStatus(@PathVariable("noAgensi") Long noAgensi) {
        return travelAgensiRestService.getStatus(noAgensi);
    }

    @GetMapping(value = "/full")
    private Mono<AgensiDetail> postStatus() {
        return travelAgensiRestService.postStatus();
    }

}
