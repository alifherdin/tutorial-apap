package apap.tutorial.pergipergi.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import java.util.List;


@Repository
public interface TourGuideDb extends JpaRepository<TourGuideModel, Long> {
    Optional<TourGuideModel> findByNoTourGuide(Long noTourGuide);
    Long deleteByNoTourGuide(Long noTourGuide);
}
