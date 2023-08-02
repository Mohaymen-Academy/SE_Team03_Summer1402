package database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Profile_Connection")
public class ProfileConnection {

    @Id
    @ManyToOne
    @JoinColumn(name ="fk_profile_1")
    private Profile profile1;

    @Id
    @ManyToOne
    @JoinColumn(name ="fk_profile_2")
    private Profile profile2;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @ManyToOne
    @JoinColumn(name ="fk_last_message_seen")
    private Message lastSeenMessage;

    public ProfileConnection() {
    }

}
