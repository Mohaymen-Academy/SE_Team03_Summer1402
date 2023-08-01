import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "profile_name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "type")
    private ProfileType type;

}
