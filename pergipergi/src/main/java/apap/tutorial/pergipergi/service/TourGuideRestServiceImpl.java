package apap.tutorial.pergipergi.service;


import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;


@Service
@Transactional
public class TourGuideRestServiceImpl implements TourGuideRestService {
    @Autowired
    private TourGuideDb tourGuideDb;


    @Override
    public TourGuideModel getTGByNoTG(Long noTG) {
        Optional<TourGuideModel> tg = tourGuideDb.findById(noTG);

        if (tg.isPresent()) {
            return tg.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<TourGuideModel> retrieveListTG() {
        return tourGuideDb.findAll();
    }

    @Override
    public TourGuideModel createTG(TourGuideModel tg) {
        return tourGuideDb.save(tg);
    }

    @Override
    public TourGuideModel updateTG(Long noTG, TourGuideModel TGupdate) {
        LocalTime now = LocalTime.now();
        TourGuideModel tg = getTGByNoTG(noTG);
        TravelAgensiModel ta = tg.getAgensi();

        if (now.isBefore(ta.getWaktuBuka()) || now.isAfter(ta.getWaktuTutup())) {
            tg.setNamaTourGuide(TGupdate.getNamaTourGuide());
            tg.setJenisKelamin(TGupdate.getJenisKelamin());
            tg.setUmur(TGupdate.getUmur());

            return tourGuideDb.save(tg);
        } else {
            throw new UnsupportedOperationException("Agensi still open!");
        }
        
    }

    @Override
    public void deleteTG(Long noTG) {
        LocalTime now = LocalTime.now();
        TourGuideModel tg = getTGByNoTG(noTG);
        TravelAgensiModel ta = tg.getAgensi();
        
        if (now.isBefore(ta.getWaktuBuka()) || now.isAfter(ta.getWaktuTutup())) {
            tourGuideDb.delete(tg);
        } else {
            throw new UnsupportedOperationException("Agensi still open!");
        }
    }

    @Override
    public TourGuideModel umurTG(Integer age, TourGuideModel tg) {
        LocalTime now = LocalTime.now();
        TravelAgensiModel ta = tg.getAgensi();

        if (now.isBefore(ta.getWaktuBuka()) || now.isAfter(ta.getWaktuTutup())) {
            tg.setUmur(age);

            return tourGuideDb.save(tg);
        } else {
            throw new UnsupportedOperationException("Agensi still open!");
        }
    }
    
    
}
