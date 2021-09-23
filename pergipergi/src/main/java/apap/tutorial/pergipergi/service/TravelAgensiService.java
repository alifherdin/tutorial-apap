package apap.tutorial.pergipergi.service;

import java.util.List;
import apap.tutorial.pergipergi.model.TravelAgensiModel;

public interface TravelAgensiService {
    void addAgensi(TravelAgensiModel travelAgensi);

    List<TravelAgensiModel> getListAgensi();

    TravelAgensiModel getAgensiByNoAgensi(Long noAgensi);

    TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi);

    List<TravelAgensiModel> getListAgensiSortName();

    void hapusAgensi(Long noAgensi);
}
