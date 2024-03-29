package apap.tutorial.pergipergi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.List;
import java.time.LocalTime;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.DestinasiService;
import apap.tutorial.pergipergi.service.TravelAgensiService;



@Controller
public class TravelAgensiController {

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @Qualifier("destinasiServiceImpl")
    @Autowired
    DestinasiService destinasiService;

    private int jmlDest = 0;

    @GetMapping("agensi/add")
    public String addAgensiFormPage(Model model) {
        model.addAttribute("agensi", new TravelAgensiModel());
        List<DestinasiModel> daftar = destinasiService.getListDestinasi();

        model.addAttribute("jmlDest", jmlDest);
        model.addAttribute("listDestinasi", daftar);

        return "form-add-agensi";
    }


    @PostMapping("agensi/add")
    public String addAgensiSubmitPage(@ModelAttribute TravelAgensiModel agensi, Model model) {
        travelAgensiService.addAgensi(agensi);

        model.addAttribute("noAgensi", agensi.getNoAgensi());


        return "add-agensi";
    }


    @GetMapping("agensi/viewall")
    public String listAgensi(Model model) {
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);

        return "viewall-agensi";
    }

    @GetMapping("agensi/viewall-sortname")
    public String listAgensiDariNama(Model model) {
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensiSortName();
        model.addAttribute("listAgensi", listAgensi);

        return "viewall-agensi";
    }


    @GetMapping("agensi/view")
    public String viewDetailAgensiPage(@RequestParam(value = "noAgensi") Long noAgensi, Model model) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        List<TourGuideModel> listTourGuide = agensi.getListTourGuide();
        List<DestinasiModel> listDestinasi = agensi.getListDestinasi();

        model.addAttribute("agensi", agensi);
        model.addAttribute("listTourGuide", listTourGuide);
        model.addAttribute("listDestinasi", listDestinasi);

        return "view-agensi";
    }


    @GetMapping("/agensi/update/{noAgensi}")
    public String updateAgensiFormPage(@PathVariable Long noAgensi, Model model) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        model.addAttribute("agensi", agensi);

        return "form-update-agensi";
    }


    @PostMapping("/agensi/update")
    public String updateAgensiSubmitPage(@ModelAttribute TravelAgensiModel agensi, Model model) {
        TravelAgensiModel updatedAgensi = travelAgensiService.updateAgensi(agensi);
        model.addAttribute("noAgensi", updatedAgensi.getNoAgensi());

        return "update-agensi";
    }

    public boolean tesWaktu(LocalTime o, LocalTime c) {
        LocalTime skrg = LocalTime.now();

        if (skrg.isAfter(o) && skrg.isBefore(c)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/agensi/hapus/{noAgensi}")
    public String hapusAgensiNow(@PathVariable Long noAgensi, Model model) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        int jmlGuide = agensi.getListTourGuide().size();
        LocalTime open = agensi.getWaktuBuka();
        LocalTime close = agensi.getWaktuTutup();

        if (tesWaktu(open, close) == false && jmlGuide == 0) {
            travelAgensiService.hapusAgensi(noAgensi);
            model.addAttribute("agensi", noAgensi);

            return "hapus-agensi";
        } else {
            return "kosong";
        }
    }


    @PostMapping("/agensi/tambahrow")
    public String tambahRowDestinasi(@ModelAttribute TravelAgensiModel agensi, Model model) {
        jmlDest += 1;

        return addAgensiFormPage(model);
    }

    @PostMapping("/agensi/hapusrow")
    public String hapusRowDestinasi(@ModelAttribute TravelAgensiModel agensi, Model model) {
        jmlDest -= 1;

        return addAgensiFormPage(model);
    }
}
