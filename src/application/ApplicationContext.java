package application;

import model.PatientParticle;
import model.SimplePatientParticle;

public class ApplicationContext {
    private String name;
    private String version;
    private PatientParticle patient;

    public ApplicationContext(String name, String version) {
        this.name = name;
        this.version = version;
        //patient = new SimplePatientParticle();
        //renderer = Renderer(patientlist);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void setPatient(PatientParticle patient) {
        this.patient = patient;
    }

    public PatientParticle getPatient() {
        return patient;
    }
}
