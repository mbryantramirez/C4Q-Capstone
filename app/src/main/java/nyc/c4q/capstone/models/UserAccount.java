package nyc.c4q.capstone.models;

public class UserAccount {

    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String userStory;
    public UserAccount(){

    }

    public UserAccount(String firstName, String lastName, String address,  String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }
}
