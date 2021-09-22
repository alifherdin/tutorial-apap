package apap.tutorial.pergipergi.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import apap.tutorial.pergipergi.model.TravelAgensiModel;

@Service
public class TravelAgensiServiceImpl implements TravelAgensiService {

    private List<TravelAgensiModel> listAgensi;

    public TravelAgensiServiceImpl() {
        listAgensi = new ArrayList<>();
    }

    @Override
    public void addAgensi(TravelAgensiModel travelAgensiModel) {
        listAgensi.add(travelAgensiModel);
        
    }

    @Override
    public List<TravelAgensiModel> getListAgensi() {
        return listAgensi;
    }

    @Override
    public TravelAgensiModel getAgensyByIdAgensi(String idAgensi) {
        for (int i = 0; i < listAgensi.size(); i++) {
            TravelAgensiModel temp = listAgensi.get(i);
            if (temp.getIdAgensi().equals(idAgensi)) {
                return temp;
            }
        }

        return null;
    }

    @Override
    public boolean gantiNomorTelepon (String idAgensi, String noTelepon) {
        for (int i = 0; i < listAgensi.size(); i++) {
            if (listAgensi.get(i).getIdAgensi().equals(idAgensi)) {
                listAgensi.get(i).setNoTelepon(noTelepon);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hapusAgensi(String idAgensi) {
        for (int i = 0; i < listAgensi.size(); i++) {
            TravelAgensiModel temp = listAgensi.get(i);
            if (temp.getIdAgensi().equals(idAgensi)) {
                listAgensi.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hapusAgensiStrip(String idAgensi) {
        for (int i = 0; i < listAgensi.size(); i++) {
            TravelAgensiModel temp = listAgensi.get(i);
            if (temp.getIdAgensi().equals(idAgensi)) {
                if (temp.getNoTelepon().equals("-")) {
                    listAgensi.remove(i);
                    return true;
                }
            }
        }

        return false;
    }
    
}
