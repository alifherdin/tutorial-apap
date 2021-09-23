package apap.tutorial.pergipergi.service;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import java.util.Optional;


@Service
@Transactional
public class TourGuideServiceImpl implements TourGuideService {

    @Autowired
    TourGuideDb tourGuideDb;

    @Override
    public void addTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.save(tourGuide);
        
    }

    @Override
    public TourGuideModel ambilDariNoGuide(Long noTourGuide) {
        Optional<TourGuideModel> tgs = tourGuideDb.findByNoTourGuide(noTourGuide);

        if (tgs.isPresent()) {
            return tgs.get();
        } else {
            return null;
        }
    }

    @Override
    public TourGuideModel updateTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.save(tourGuide);
        
        return tourGuide;
    }

    @Override
    public void hapusTourGuide(Long x) {
        tourGuideDb.deleteByNoTourGuide(x);
        
    }
    
}
