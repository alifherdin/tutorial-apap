package apap.tutorial.pergipergi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.repository.DestinasiDb;


@Service
@Transactional
public class DestinasiRestServiceImpl implements DestinasiRestService {

    @Autowired
    DestinasiDb destinasiDb;

    
    @Override
    public DestinasiModel createDestinasi(DestinasiModel destinasi) {
        return destinasiDb.save(destinasi);
    }

    @Override
    public DestinasiModel getDestByNoDest(Long noDestinasi) {
        Optional<DestinasiModel> destinasi = destinasiDb.findById(noDestinasi);

        if (destinasi.isPresent()) {
            return destinasi.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteDestinasi(Long noDestinasi) {
        DestinasiModel dest = getDestByNoDest(noDestinasi);

        destinasiDb.delete(dest);
    }

    @Override
    public DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasiUpdate) {
        DestinasiModel destinasi = getDestByNoDest(noDestinasi);

        destinasi.setNegaraDestinasi(destinasiUpdate.getNegaraDestinasi());
        destinasi.setIsBebasVisa(destinasiUpdate.getIsBebasVisa());

        return destinasiDb.save(destinasi);
    }

    @Override
    public List<DestinasiModel> retrieveListDestinasi() {
        return destinasiDb.findAll();
    }
    
    
}
