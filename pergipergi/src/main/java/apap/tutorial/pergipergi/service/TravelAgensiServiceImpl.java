package apap.tutorial.pergipergi.service;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TravelAgencyDb;

@Service
@Transactional
public class TravelAgensiServiceImpl implements TravelAgensiService {

    @Autowired
    TravelAgencyDb travelAgensiDb;

    @Override
    public void addAgensi(TravelAgensiModel travelAgensi) {
        travelAgensiDb.save(travelAgensi);
        
    }

    @Override
    public List<TravelAgensiModel> getListAgensi() {
        return travelAgensiDb.findAll();
    }

    @Override
    public TravelAgensiModel getAgensiByNoAgensi(Long noAgensi) {
        Optional<TravelAgensiModel> agensi = travelAgensiDb.findByNoAgensi(noAgensi);

        if (agensi.isPresent()) {
            return agensi.get();
        } else {
            return null;
        }
    }

    @Override
    public TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi) {
        travelAgensiDb.save(travelAgensi);

        return travelAgensi;
    }


    
    @Override
    public List<TravelAgensiModel> getListAgensiSortName() {
        return travelAgensiDb.findAllByOrderByNamaAgensiAsc();
    }

    @Override
    public void hapusAgensi(Long noAgensi) {
        travelAgensiDb.deleteById(noAgensi);
    }

    @Override
    public boolean isClosed(LocalTime waktuBuka, LocalTime waktuTutup) {
        LocalTime now = LocalTime.now();
        if(now.isBefore(waktuBuka) || now.isAfter(waktuTutup)) {
            return true;
        }
        return false;
    }
}
