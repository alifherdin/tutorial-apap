package apap.tutorial.pergipergi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.List;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;

@Controller
public class TravelAgensiController {
    @Autowired
    private TravelAgensiService travelAgensiService;

    @RequestMapping("agensi/add")
    public String addAgensi(
        @RequestParam(value = "idAgensi", required = true) String idAgensi,
        @RequestParam(value = "namaAgensi", required = true) String namaAgensi,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon, Model model
    ) {
        TravelAgensiModel agensi = new TravelAgensiModel(idAgensi, namaAgensi, alamat, noTelepon);
        travelAgensiService.addAgensi(agensi);

        model.addAttribute("idAgensi", idAgensi);


        return "add-agensi";
    }
    

    @RequestMapping("agensi/viewAll")
    public String listAgensi(Model model) {
        List<TravelAgensiModel>  listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
        
        return "viewall-agensi";
    }

    @RequestMapping({"agensi/view/idAgensi", "agensi/view/idAgensi/{idAgensi}"})
    public String detailAgensi(@PathVariable(required = false) String idAgensi, Model model) {
        System.out.println("ID agensi adalah: " + idAgensi);
        TravelAgensiModel  agensi = travelAgensiService.getAgensyByIdAgensi(idAgensi);
        model.addAttribute("agensi", agensi);
        
        if (agensi != null) {
            return "view-agensi";
        } else {
            return "kosong";
        }

    }

    @RequestMapping({"agensi/update/id-agensi", "agensi/update/id-agensi/{idAgensi}/no-telepon/{noTelepon}"})
    public String gantiNomorTelepon(
        @PathVariable(value = "idAgensi", required = false) String idAgensi,
        @PathVariable(value = "noTelepon", required = false) String noTelepon,
        Model model) {

        boolean temp = travelAgensiService.gantiNomorTelepon(idAgensi, noTelepon);

        TravelAgensiModel agensi = travelAgensiService.getAgensyByIdAgensi(idAgensi);
        model.addAttribute("agensi", agensi);
                
        if (temp != false) {
            return "ganti-telepon";
        } else {
            return "kosong";
        }
    }

    @RequestMapping({"agensi/delete/id-agensi", "agensi/delete/id-agensi/{idAgensi}"})
    public String hapusAgensiById(@PathVariable(value = "idAgensi", required = false) String idAgensi, Model model) {
        boolean temp = travelAgensiService.hapusAgensi(idAgensi);

        List<TravelAgensiModel>  listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
          
        if (temp != false) {
            return "hapus-agensi";
        } else {
            return "kosong";
        }
    }

}
