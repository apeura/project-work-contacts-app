package util;

/**
* Person class contains set and get methods for Person's contact information 
* as well as an overridden toString method and Person constructor.
*
* @author Anni Peura
*
*/
public class Person { 
    
/**
 * The unique identifier for a person.
 */
protected String id;

/**
 * The first name of a person.
 */
protected String firstName;

/**
 * The last name of a person.
 */
protected String lastName;

/**
 * The phone number of a person.
 */
protected String phoneNumber;

/**
 * The email address of a person.
 */
protected String emailAddress;

/**
 * The address of a person.
 */
protected String address;

    /**
    * Runs set validations for all user submitted Person values
    *
    * @param id is the String sent to setId 
    * @param firstName is the String sent to setFirstName
    * @param lastName is the String sent to setLastName 
    * @param phoneNumber is the String sent to setPhoneNumber
    * @param emailAddress is the String sent to setEmail
    * @param address is the String sent to setAddress
    */
    public Person(String id, String firstName, String lastName, 
        String phoneNumber, String emailAddress, String address) {

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(emailAddress);
        setAddress(address);
    } 

    /**
    * Turns every Person's data to a String, divided with ",,,"
    *
    * @return a string of the person's information divided with ",,,"
    */
    @Override
    public String toString() {
        String personInfo = "";
        return id + ",,," + firstName + ",,," + lastName + ",,," 
        + phoneNumber + ",,," + emailAddress + ",,," + address;

    }

    /**
    * setId contains set method for id which is validated to MMDDYYAXXX format, 
    * if user's feed is invalid throws IllegalArgumentException
    *
    * @throws IllegalArgumentException if input doesn't match pattern
    * @param n is the String setId validates 
    */
    public void setId(String n) {
        String idPattern 
                = "\\d\\d\\d\\d\\d\\d([\\w,\\W])\\d\\d\\d([\\d,\\a-zA-Z])";
        if (n.matches(idPattern)) {
            id = n;
        } else {
            throw new IllegalArgumentException("id must be MMDDYYAXXXX");
        }
    }
    /**
    * getId contains get method for id
    *
    * @return method returns id 
    */
    public String getId() {
        return id;
    }

    /**
    * setFirstName contains setter for first name, if user's feed is less than 2 
    * or more than 20 characters throws IllegalArgumentException
    *
    * @throws IllegalArgumentException if input is not 2-20 characters
    * @param n is the String setFirstName validates 
    */
    public void setFirstName(String n) {
        if (n.length() < 2 || n.length() > 20) {
            throw new IllegalArgumentException("name must be 2-20 characters");
        } else {
            firstName = n;
        }
    }
    /**
    * getfirstName contains get method for first name
    *
    * @return method returns first name
    */
    public String getFirstName() {
        return firstName;
    }

    /**
    * setLastName contains set method for last name, if user's feed is less than 
    * 2 or more than 20 characters throws IllegalArgumentException
    *
    * @throws IllegalArgumentException if input is not 2-20 characters
    * @param n is the String setLastName validates 
    */
    public void setLastName(String n) {
        if (n.length() < 2 || n.length() > 20) {
            throw new IllegalArgumentException("name must be 2-20 characters");
        } else {
            lastName = n;
        }
    }
    /**
    * getLastName contains get method for last name
    *
    * @return method returns last name
    */
    public String getLastName() {
        return lastName;
    }

    /**
    * setPhoneNumber contains set method for phone number, if user's feed is 
    * doesn't match the pattern throws IllegalArgumentException
    *
    * @throws IllegalArgumentException if input doesn't match the pattern
    * @param n is the String setPhoneNumber validates 
    */
    public void setPhoneNumber(String n) {
        String phoneNumberPattern = "([\\w,\\W])?\\d{3,20}";
        if (!n.matches(phoneNumberPattern)) {
            throw new IllegalArgumentException("phoneNumber length 3-20");
        } else {
            phoneNumber = n;
        }
    }    
    /**
    * getPhoneNumber contains get method for phone number
    *
    * @return method returns phone number
    */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
    * setEmail contains set method for email, if user's feed is more than 50
    * characters throws IllegalArgumentException, if String is empty sets
    * emailAddress as dash(-)
    *
    * @throws IllegalArgumentException if input is not less than 50 characters
    * @param n is the String setEmail validates 
    */
    public void setEmail(String n) {
        if ( n.length() > 50) {
            throw new IllegalArgumentException("email must be < 50 char");
        } else if (n.length() == 0) {
            emailAddress = "-";
        } else {
            emailAddress = n;
        }
    }
    /**
    * getEmail contains get method for email
    *
    * @return method returns email
    */
    public String getEmail() {
        return emailAddress;
    }

    /**
    * setAddress contains set method for address, if user's feed is more than 50
    * characters throws IllegalArgumentException, if String is empty sets
    * address as dash(-)
    *
    * @throws IllegalArgumentException if input is not less than 50 characters
    * @param n is the String setAddress validates 
    */
    public void setAddress(String n) {
        if (n.length() > 50) {
            throw new IllegalArgumentException("must be less than 50 char");
        } else if (n.length() == 0) {
            address = "-";
        } else {
            address = n;
        }
    }
    /**
    * getAddress contains get method for address
    *
    * @return method returns address
    */
    public String getAddress() {
        return address;
    }
}