package vet_clinic;

public class patientInfo {
    private int chipID;
    private String ownerName;
    
    public patientInfo(int chipID, String ownerName) {
        this.chipID = chipID;
        this.ownerName = ownerName;
    }
    
    public int getChipID() {
        return chipID;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
}
