package apap.tutorial.pergipergi.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;


public interface TourGuideRestService {
    TourGuideModel getTGByNoTG(Long noTG);

    List<TourGuideModel> retrieveListTG();

    TourGuideModel createTG(TourGuideModel tg);

    TourGuideModel updateTG(Long noTG, TourGuideModel TGupdate);

    void deleteTG(Long noTG);

    TourGuideModel umurTG(Integer age, TourGuideModel tg);
}
