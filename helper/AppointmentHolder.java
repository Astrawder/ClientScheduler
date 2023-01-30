package helper;

import com.company.Appointment;
/**
 * Singleton class used for holding an appointment instance.
 *
 * @author Aidan Strawder
 */
public class AppointmentHolder {
    private Appointment appointment;
    private final static AppointmentHolder INSTANCE = new AppointmentHolder();
    /**
     * Private default class constructor.
     *
     */
    private AppointmentHolder(){}
    /**
     * @return the appointment INSTANCE
     */
    public static helper.AppointmentHolder getInstance(){return INSTANCE;}
    /**
     * @param appointment the appointment to set
     */
    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }
    /**
     * @return the appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }
}
