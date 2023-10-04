package car_pool_sharing_client.Models;

public class Driver {

    private Integer driver_Id;
    private String name;
    private String contact;
    private String licenseNumber;

    public Driver() {

    }
    public Driver(Integer driver_Id, String name, String contact, String licenseNumber) {
        this.driver_Id = driver_Id;
        this.name = name;
        this.contact = contact;
        this.licenseNumber = licenseNumber;
    }

    public Integer getDriver_Id() {
        return driver_Id;
    }

    public void setDriver_Id(Integer driver_Id) {
        this.driver_Id = driver_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Driver: ");
        builder.append("\tID: " + getDriver_Id());
        builder.append("\tName: " + getName());
        builder.append("\tContact: " + getContact());
        builder.append("\tLicense Number: " + getLicenseNumber());
        return builder.toString();
    }
}
