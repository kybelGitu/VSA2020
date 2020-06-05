/*
 * Author:  andbin - http://andbin.altervista.org
 * License: Public Domain
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR OTHERWISE, INCLUDING WITHOUT LIMITATION, ANY
 * WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 */
package cv3swingapp;





public class Person implements Cloneable {
    private String firstName;
    private String lastName;
    private int birthYear;
    private boolean employed;

    public Person() { }

    public Person(String firstName, String lastName, int birthYear, boolean employed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.employed = employed;
    }

    // Getter methods.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public boolean isEmployed() {
        return employed;
    }

    // Setter methods.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    // toString() method for a textual description.
    public String toString() {
        return firstName + " " + lastName + ", " + birthYear + " (" + (employed ? "" : "not ") + "employed)";
    }

    // clone() method to create a copy of the object.
    public Object clone() {
        // int/boolean are primitive, String is immutable. For this reasons
        // it's sufficient to clone all fields with clone() of Object!
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // Should NOT happen, this class implements Cloneable!
            throw new Error("Internal error!");
        }
    }
}