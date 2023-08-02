package database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password_hash")
    private byte[] passwordHash;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Setter
    @OneToOne
    @JoinColumn(name ="fk_profile_id")
    private Profile profile;

    public Account(String username, byte[] passwordHash, String phoneNumber) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
    }

}
