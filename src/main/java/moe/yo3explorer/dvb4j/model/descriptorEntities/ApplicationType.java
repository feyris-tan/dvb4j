package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class ApplicationType
{
    public ApplicationType(int applicationType, int versionNumber) {
        this.applicationType = applicationType;
        this.versionNumber = versionNumber;
    }

    private int applicationType;
    private int versionNumber;

    public int getApplicationType() {
        return applicationType;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toString() {
        return "ApplicationType{" +
                "applicationType=" + applicationType +
                ", versionNumber=" + versionNumber +
                '}';
    }
}
