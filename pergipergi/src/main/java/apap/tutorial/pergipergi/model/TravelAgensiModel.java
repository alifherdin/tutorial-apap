package apap.tutorial.pergipergi.model;

public class TravelAgensiModel {
    private String idAgensi;
    private String namaAgensi;
    private String alamat;
    private String noTelepon;

    public TravelAgensiModel(String idAgensi, String namaAgensi, String alamat, String noTelepon) {
        this.idAgensi = idAgensi;
        this.namaAgensi = namaAgensi;
        this.alamat = alamat;
        this.noTelepon = noTelepon;

    }

    public String getIdAgensi() {
        return this.idAgensi;
    }

    public void setIdAgensi(String idAgensi) {
        this.idAgensi = idAgensi;
    }

    public String getNamaAgensi() {
        return this.namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getAlamatAgensi() {
        return this.alamat;
    }

    public void setAlamatAgensi(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

}
