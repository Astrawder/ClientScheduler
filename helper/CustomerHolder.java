package helper;

import com.company.Customer;
/**
 * Singleton class used for holding a customer instance.
 *
 * @author Aidan Strawder
 */
public class CustomerHolder {
    private Customer customer;
    private final static CustomerHolder INSTANCE = new CustomerHolder();
    /**
     * Private default class constructor.
     *
     */
    private CustomerHolder(){}
    /**
     * @return the customer INSTANCE
     */
    public static CustomerHolder getInstance(){
        return INSTANCE;
    }
    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}
