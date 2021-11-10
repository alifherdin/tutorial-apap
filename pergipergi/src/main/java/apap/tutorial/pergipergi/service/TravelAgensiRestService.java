package apap.tutorial.pergipergi.service;


import java.time.LocalTime;
import java.util.List;
import reactor.core.publisher.Mono;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;


public interface TravelAgensiRestService {
    TravelAgensiModel createAgensi(TravelAgensiModel travelAgensi);

    List<TravelAgensiModel> retrieveListAgensi();

    TravelAgensiModel getAgensiByNoAgensi(Long noAgensi);

    TravelAgensiModel updateAgensi(Long noAgensi, TravelAgensiModel agensiUpdate);

    void deleteAgensi(Long noAgensi);

    Mono<String> getStatus(Long noAgensi);

    Mono<AgensiDetail> postStatus();

    // List<TravelAgensiModel> getListAgensiSortName();

    // boolean isClosed(LocalTime waktuBuka, LocalTime waktuTutup);
}
