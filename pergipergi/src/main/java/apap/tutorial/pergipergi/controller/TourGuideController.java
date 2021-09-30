package apap.tutorial.pergipergi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalTime;
import java.util.List;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import apap.tutorial.pergipergi.service.TourGuideService;


@Controller
public class TourGuideController {

    @Qualifier("tourGuideServiceImpl")
    @Autowired
    private TourGuideService tourGuideService;

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @GetMapping("tour-guide/add/{noAgensi}")
    public String addTourGuideFormPage(@PathVariable Long noAgensi, Model model) {
        TourGuideModel guide = new TourGuideModel();
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        guide.setAgensi(agensi);
        model.addAttribute("guide", guide);

        return "form-add-tour-guide";
    }

    @PostMapping("/tour-guide/add") 
    public String addTourGuideSubmitPage(@ModelAttribute TourGuideModel tourGuide, Model model) {
        tourGuideService.addTourGuide(tourGuide);
        model.addAttribute("noTourGuide", tourGuide.getNoTourGuide());

        return "add-tour-guide";
    }    
    

    public boolean tesWaktu(LocalTime o, LocalTime c) {
        LocalTime skrg = LocalTime.now();

        if (skrg.isAfter(o) && skrg.isBefore(c)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/tour-guide/update/{noTourGuide}")
    public String updateGuideFormPage(@PathVariable Long noTourGuide, Model model) {
        TourGuideModel tg = tourGuideService.ambilDariNoGuide(noTourGuide);
        TravelAgensiModel ag = tg.getAgensi();

        LocalTime open = ag.getWaktuBuka();
        LocalTime close = ag.getWaktuTutup();
        Long idA = ag.getNoAgensi();

        if (tesWaktu(open, close) == false) {
            model.addAttribute("guide", tg);
            model.addAttribute("agensi", idA);
    
            return "form-update-tour-guide";
        } else {
            return "kosong";
        }

    }

    @PostMapping("/tour-guide/update")
    public String updateGuideSubmitPage(@ModelAttribute TourGuideModel guide, Model model) {
        TourGuideModel updatedGuide = tourGuideService.updateTourGuide(guide);
        model.addAttribute("noGuide", updatedGuide.getNoTourGuide());

        return "update-tour-guide";
    }
    
    
    @PostMapping("/tour-guide/delete")
    public String deleteTourGuideSubmit(@ModelAttribute TravelAgensiModel agensi, Model model) {
        model.addAttribute("noAgensi", agensi.getNoAgensi());

        if (travelAgensiService.isClosed(agensi.getWaktuBuka(), agensi.getWaktuTutup())) {
            for (TourGuideModel tourGuide : agensi.getListTourGuide()) {
                tourGuideService.hapusTourGuide(tourGuide);
            }

            return "hapus-tour-guide";
        }

        return "kosong";
    }


    // @GetMapping("/tour-guide/hapus/{noTourGuide}")
    // public String delGuideFormPage(@PathVariable Long noTourGuide, Model model) {
    //     TourGuideModel tg = tourGuideService.ambilDariNoGuide(noTourGuide);
    //     TravelAgensiModel ag = tg.getAgensi();
    //     LocalTime open = ag.getWaktuBuka();
    //     LocalTime close = ag.getWaktuTutup();

    //     if (tesWaktu(open, close) == false) {
    //         tourGuideService.hapusTourGuide(noTourGuide);
    //         model.addAttribute("noGuide", noTourGuide);
    
    //         return "hapus-tour-guide";
    //     } else {
    //         return "kosong";
    //     }

    // }

}
