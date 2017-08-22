package model;

public class SimplePatientParticle implements PatientParticle {

    private long arrival;
    private long medicalExamination;
    private long discharge;

    public SimplePatientParticle(long arrival, long medicalExamination, long discharge) {
        this.arrival = arrival;
        this.medicalExamination = medicalExamination;
        this.discharge = discharge;
    }

    public long getArrival() {
        return arrival;
    }

    public void setArrival(long arrival) {
        this.arrival = arrival;
    }

    public long getMedicalExamination() {
        return medicalExamination;
    }

    public void setMedicalExamination(long medicalExamination) {
        this.medicalExamination = medicalExamination;
    }

    public long getDischarge() {
        return discharge;
    }

    public void setDischarge(long discharge) {
        this.discharge = discharge;
    }

    @Override
    public long calculateWaitingDuration() {
        long waitingDuration = 0;
        if (arrival <= medicalExamination){
            waitingDuration =  medicalExamination - arrival;
        }
        return waitingDuration;
    }

    @Override
    public long calculateExaminationDuration() {
        long examinationDuration = 0;
        if (medicalExamination <= discharge){
            examinationDuration = discharge - medicalExamination;
        }
        return examinationDuration;
    }
}
