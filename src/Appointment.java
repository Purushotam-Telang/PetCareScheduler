import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements Serializable {
    private String appointmentType;
    private String notes;
    private LocalDate appDate;
    private LocalTime appTime;

    public Appointment(String appointmentType, LocalTime appTime, LocalDate appDate, String notes) {
        this.appointmentType = appointmentType;
        this.appTime = appTime;
        this.appDate = appDate;
        this.notes = notes;
    }

    public Appointment(String appointmentType, LocalDate appDate, LocalTime appTime) {
        this.appointmentType = appointmentType;
        this.appDate = appDate;
        this.appTime = appTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public LocalTime getAppTime() {
        return appTime;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

    public void setAppTime(LocalTime appTime) {
        this.appTime = appTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    @Override
    public String toString() {
        return  "Appointment Type = " + appointmentType +"\n" +
                "Appointment Date = " + appDate + "\n" +
                "Appointment Time = " + appTime + "\n" +
                "Notes = " + notes;
    }
}
