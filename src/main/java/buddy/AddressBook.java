package buddy;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class AddressBook {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id = 0;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<BuddyInfo>();
    }

    public AddressBook(int id) {
        this.id = id;
        buddies = new ArrayList<BuddyInfo>();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) { this.name = name; }

    public Collection<BuddyInfo> getBuddies() {
        return buddies;
    };

    public void setBuddies(Collection<BuddyInfo> buddies) {
        this.buddies = buddies;
    };

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
    }

    public void removeBuddy(String name) {

        ArrayList<BuddyInfo> removes = new ArrayList<BuddyInfo>();
        for (BuddyInfo b : buddies) {
            if (b.getName().equals(name) ){
                removes.add(b);
            }
        }
        for (BuddyInfo b : removes) {
            this.removeBuddy(b);
        }
    }

    public String toString() {
        String s = "{";
        for (BuddyInfo b : buddies) {
            s += b + "; ";
        }
        return s + "}";
    }

    public static void main(String[] args) {
        AddressBook a = new AddressBook();
        a.addBuddy(new BuddyInfo("Bob", "Drewy Lane", "Homeless"));
        a.addBuddy(new BuddyInfo("Jane", "Drewy Lane", "Homeless"));
        System.out.println(a);

    }
}