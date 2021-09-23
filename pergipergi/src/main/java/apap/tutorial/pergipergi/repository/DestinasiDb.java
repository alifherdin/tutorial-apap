package apap.tutorial.pergipergi.repository;


import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.DestinasiModel;


@Repository
public interface DestinasiDb extends JpaRepository<DestinasiModel, Long> {
    
}
