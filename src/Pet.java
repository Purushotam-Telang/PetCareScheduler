import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pet implements Serializable {
    private String petId;
    private String petName;
    private String petSpecies;
    private long petAge;
    private String petOwnerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private ArrayList<Appointment> appointmentList;

    public Pet(String petId, String petName, String petSpecies, long petAge, String petOwnerName, String contactInfo) {
        this.petId = petId;
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petAge = petAge;
        this.petOwnerName = petOwnerName;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDate.now();
        this.appointmentList = new ArrayList<>();
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }

    public long getPetAge() {
        return petAge;
    }

    public void setPetAge(long petAge) {
        this.petAge = petAge;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPetOwnerName() {
        return petOwnerName;
    }

    public void setPetOwnerName(String petOwnerName) {
        this.petOwnerName = petOwnerName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setAppointmentList(ArrayList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void addAppointment(Appointment app) {
        this.appointmentList.add(app);
    }

    @Override
    public String toString() {
        return "Pet Id = " + petId + "\n" +
                "Pet Name = "  + petName + "\n" +
                "Pet Species = " + petSpecies + "\n" +
                "Pet Age = " + petAge + "\n" +
                "Pet Owner Name = " + petOwnerName + "\n" +
                "Contact Info = " + contactInfo + "\n" +
                "Registration Date = " + registrationDate;
    }
}
