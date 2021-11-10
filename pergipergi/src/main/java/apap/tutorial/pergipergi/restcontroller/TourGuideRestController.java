package apap.tutorial.pergipergi.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.service.TourGuideRestService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1")
public class TourGuideRestController {
    @Autowired
    private TourGuideRestService tourGuideRestService;

    @PostMapping(value="/tour")
    private ResponseEntity createTG(@Valid @RequestBody TourGuideModel tg, BindingResult bindingresult) {
        if (bindingresult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            tourGuideRestService.createTG(tg);

            return ResponseEntity.ok("Create gour guide success");
        }

    }

    @GetMapping(value="/tour/{noTG}")
    private TourGuideModel retrieveTG(@PathVariable("noTG") Long noTG) {
        try {
            return tourGuideRestService.getTGByNoTG(noTG);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tour guide " + String.valueOf(noTG) + " not found.");
        }

    }

    @DeleteMapping(value="/tour/{noTG}")
    private ResponseEntity deleteTG(@PathVariable("noTG") Long noTG) {

        try {
            tourGuideRestService.deleteTG(noTG);
            return ResponseEntity.ok("Tour guide " + noTG + " has been deleted.");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tour guide " + String.valueOf(noTG) + " not found.");
        } 

    }

    @PutMapping(value="/tour/{noTG}")
    private ResponseEntity updateTG(@PathVariable("noTG") Long noTG, @RequestBody TourGuideModel tg) {

        try {
            tourGuideRestService.updateTG(noTG, tg);

            return ResponseEntity.ok("Update tour guide success");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tour guide " + String.valueOf(noTG) + " not found.");
        }catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel agensi is still open!");
        }

    }
    
    @GetMapping(value="/list-tourguide")
    private List<TourGuideModel> retrieveTG() {
        return tourGuideRestService.retrieveListTG();
    }

    @GetMapping(value="/tour/umur/{noTG}")
    private ResponseEntity ageTG(@PathVariable("noTG") Long noTG) {
        try {
            TourGuideModel temp = tourGuideRestService.getTGByNoTG(noTG);
            String addr = "https://api.agify.io?name=" + temp.getNamaTourGuide();
            RestTemplate templateRest = new RestTemplate();

            String rt = templateRest.getForObject(addr, String.class);

            String age = rt.split(",")[1].split(":")[1];

            tourGuideRestService.umurTG(Integer.parseInt(age), temp);

            return ResponseEntity.ok("Change umur tour guide success");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tour guide " + String.valueOf(noTG) + " not found.");
        }

    }

}
