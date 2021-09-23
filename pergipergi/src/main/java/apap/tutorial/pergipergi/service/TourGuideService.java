package apap.tutorial.pergipergi.service;

import java.util.List;
import apap.tutorial.pergipergi.model.TourGuideModel;

public interface TourGuideService {
    void addTourGuide(TourGuideModel tourGuide);

    TourGuideModel ambilDariNoGuide(Long noTourGuide);

    TourGuideModel updateTourGuide(TourGuideModel tourGuide);

    void hapusTourGuide(Long x);
}
