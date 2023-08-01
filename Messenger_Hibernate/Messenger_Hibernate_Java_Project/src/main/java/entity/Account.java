package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password_hash", length = 64)
    private String password;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name ="fk_profile_id")
    private Profile profile;

    public Account() {
    }

    public Account(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

}
