package buddy;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name = "";

    private String address = "";
    private String phoneNumber = "";

    public BuddyInfo() {

    }

    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setId(long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    @Override
    public String toString() {
        return name + " : " + address + " : " + phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BuddyInfo) {
            BuddyInfo nb = (BuddyInfo) obj;
            return nb.name == this.name && nb.address == this.address && nb.phoneNumber == this.phoneNumber;
        }
        return false;
    }
}