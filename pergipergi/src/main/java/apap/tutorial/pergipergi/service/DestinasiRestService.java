package apap.tutorial.pergipergi.service;


import java.util.List;

import apap.tutorial.pergipergi.model.DestinasiModel;

public interface DestinasiRestService {
    DestinasiModel createDestinasi(DestinasiModel destinasi);

    DestinasiModel getDestByNoDest(Long noDestinasi);

    void deleteDestinasi(Long noDestinasi);

    DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasiUpdate);

    List<DestinasiModel> retrieveListDestinasi();

    List<DestinasiModel> ambilDariNama(String negara);
    
}
