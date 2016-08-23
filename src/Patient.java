
/**
 * Created by Andreas on 25.07.2016.
 */


public class Patient {
    private String ID;
    private String ANF_DATUM;
    private String ANF_EREIGNIS_DATUM;
    private String ANF_GEPRUEFT_JN;
    private String ARBEITSPLATZ;
    private String ANF_PRUEFDATUM;
    private String BEREICH_SCHLUESSEL;
    private String UNTERS_NAME;
    private String ANMELDUNG_ANKUNFT;
    private String DATUM_TERMIN_VERGABE;
    private String UNTERS_BEGINN;
    private String UNTERS_ENDE;
    private String UNTERS_ART;
    private String BEFUND_DATUM;
    private String DIKTAT_DATUM;
    private String LESE_DATUM;
    private String GEGENLESE_DATUM;
    private String BEFUND_FREIGABE;

    public Patient(String ID, String ANF_DATUM, String ANF_EREIGNIS_DATUM, String ANF_GEPRUEFT_JN,
                   String ARBEITSPLATZ, String ANF_PRUEFDATUM,
                   String BEREICH_SCHLUESSEL, String UNTERS_NAME, String ANMELDUNG_ANKUNFT,
                   String DATUM_TERMIN_VERGABE, String UNTERS_BEGINN, String UNTERS_ENDE,
                   String UNTERS_ART, String BEFUND_DATUM, String DIKTAT_DATUM, String LESE_DATUM,
                   String GEGENLESE_DATUM, String BEFUND_FREIGABE) {
        this.ID = ID;
        this.ANF_DATUM = ANF_DATUM;
        this.ANF_EREIGNIS_DATUM = ANF_EREIGNIS_DATUM;
        this.ANF_GEPRUEFT_JN = ANF_GEPRUEFT_JN;
        this.ARBEITSPLATZ = ARBEITSPLATZ;
        this.ANF_PRUEFDATUM = ANF_PRUEFDATUM;
        this.BEREICH_SCHLUESSEL = BEREICH_SCHLUESSEL;
        this.UNTERS_NAME = UNTERS_NAME;
        this.ANMELDUNG_ANKUNFT = ANMELDUNG_ANKUNFT;
        this.DATUM_TERMIN_VERGABE = DATUM_TERMIN_VERGABE;
        this.UNTERS_BEGINN = UNTERS_BEGINN;
        this.UNTERS_ENDE = UNTERS_ENDE;
        this.UNTERS_ART = UNTERS_ART;
        this.BEFUND_DATUM = BEFUND_DATUM;
        this.DIKTAT_DATUM = DIKTAT_DATUM;
        this.LESE_DATUM = LESE_DATUM;
        this.GEGENLESE_DATUM = GEGENLESE_DATUM;
        this.BEFUND_FREIGABE = BEFUND_FREIGABE;
    }

    /**
     * public int getID() {
     * return ID;
     * }
     * <p>
     * public void setID(int ID) {
     * this.ID = ID;
     * }
     **/

    public String getANF_DATUM() {
        return ANF_DATUM;
    }

    public void setANF_DATUM(String ANF_DATUM) {
        this.ANF_DATUM = ANF_DATUM;
    }

    public String getANF_GEPRUEFT_JN() {
        return ANF_GEPRUEFT_JN;
    }

    public void setANF_GEPRUEFT_JN(String ANF_GEPRUEFT_JN) {
        this.ANF_GEPRUEFT_JN = ANF_GEPRUEFT_JN;
    }

    public String getANF_EREIGNIS_DATUM() {
        return ANF_EREIGNIS_DATUM;
    }

    public void setANF_EREIGNIS_DATUM(String ANF_EREIGNIS_DATUM) {
        this.ANF_EREIGNIS_DATUM = ANF_EREIGNIS_DATUM;
    }

    public String getARBEITSPLATZ() {
        return ARBEITSPLATZ;
    }

    public void setARBEITSPLATZ(String ARBEITSPLATZ) {
        this.ARBEITSPLATZ = ARBEITSPLATZ;
    }

    public String getANF_PRUEFDATUM() {
        return ANF_PRUEFDATUM;
    }

    public void setANF_PRUEFDATUM(String ANF_PRUEFDATUM) {
        this.ANF_PRUEFDATUM = ANF_PRUEFDATUM;
    }

    public String getBEREICH_SCHLUESSEL() {
        return BEREICH_SCHLUESSEL;
    }

    public void setBEREICH_SCHLUESSEL(String BEREICH_SCHLUESSEL) {
        this.BEREICH_SCHLUESSEL = BEREICH_SCHLUESSEL;
    }

    public String getUNTERS_NAME() {
        return UNTERS_NAME;
    }

    public void setUNTERS_NAME(String UNTERS_NAME) {
        this.UNTERS_NAME = UNTERS_NAME;
    }

    public String getANMELDUNG_ANKUNFT() {
        return ANMELDUNG_ANKUNFT;
    }

    public void setANMELDUNG_ANKUNFT(String ANMELDUNG_ANKUNFT) {
        this.ANMELDUNG_ANKUNFT = ANMELDUNG_ANKUNFT;
    }

    public String getDATUM_TERMIN_VERGABE() {
        return DATUM_TERMIN_VERGABE;
    }

    public void setDATUM_TERMIN_VERGABE(String DATUM_TERMIN_VERGABE) {
        this.DATUM_TERMIN_VERGABE = DATUM_TERMIN_VERGABE;
    }

    public String getUNTERS_BEGINN() {
        return UNTERS_BEGINN;
    }

    public void setUNTERS_BEGINN(String UNTERS_BEGINN) {
        this.UNTERS_BEGINN = UNTERS_BEGINN;
    }

    public String getUNTERS_ENDE() {
        return UNTERS_ENDE;
    }

    public void setUNTERS_ENDE(String UNTERS_ENDE) {
        this.UNTERS_ENDE = UNTERS_ENDE;
    }

    public String getUNTERS_ART() {
        return UNTERS_ART;
    }

    public void setUNTERS_ART(String UNTERS_ART) {
        this.UNTERS_ART = UNTERS_ART;
    }

    public String getBEFUND_DATUM() {
        return BEFUND_DATUM;
    }

    public void setBEFUND_DATUM(String BEFUND_DATUM) {
        this.BEFUND_DATUM = BEFUND_DATUM;
    }

    public String getDIKTAT_DATUM() {
        return DIKTAT_DATUM;
    }

    public void setDIKTAT_DATUM(String DIKTAT_DATUM) {
        this.DIKTAT_DATUM = DIKTAT_DATUM;
    }

    public String getLESE_DATUM() {
        return LESE_DATUM;
    }

    public void setLESE_DATUM(String LESE_DATUM) {
        this.LESE_DATUM = LESE_DATUM;
    }

    public String getGEGENLESE_DATUM() {
        return GEGENLESE_DATUM;
    }

    public void setGEGENLESE_DATUM(String GEGENLESE_DATUM) {
        this.GEGENLESE_DATUM = GEGENLESE_DATUM;
    }

    public String getBEFUND_FREIGABE() {
        return BEFUND_FREIGABE;
    }

    public void setBEFUND_FREIGABE(String BEFUND_FREIGABE) {
        this.BEFUND_FREIGABE = BEFUND_FREIGABE;
    }
}
