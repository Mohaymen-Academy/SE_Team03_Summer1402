import jakarta.persistence.*;

@Entity
@Table(name = "Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "profile_name", length = 50)
    private String name;

    @Column(name = "bio", length = 256)
    private String bio;

    @ManyToOne
    @JoinColumn(name ="fk_image_id")
    private File image;

    @Column(name = "type")
    private ProfileType type;

    @OneToOne
    @JoinColumn(name ="fk_account_id")
    private Account account;

    public Profile() {
    }

    public Profile(String name, String bio, File image, ProfileType type, Account account) {
        this.name = name;
        this.bio = bio;
        this.image = image;
        this.type = type;
        this.account = account;
    }

}
