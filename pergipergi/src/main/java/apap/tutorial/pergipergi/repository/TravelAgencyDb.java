package apap.tutorial.pergipergi.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import java.util.List;


@Repository
public interface TravelAgencyDb extends JpaRepository<TravelAgensiModel, Long> {
    Optional<TravelAgensiModel> findByNoAgensi(Long noAgensi);

    List<TravelAgensiModel> findAllByOrderByNamaAgensiAsc();

    Long deleteByNoAgensi(Long noAgensi);
}
