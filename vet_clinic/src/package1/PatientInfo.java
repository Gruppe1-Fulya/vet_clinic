package package1;

public class PatientInfo {
    private int chipID;
    private String ownerName;
    
    public PatientInfo(int chipID, String ownerName) {
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
