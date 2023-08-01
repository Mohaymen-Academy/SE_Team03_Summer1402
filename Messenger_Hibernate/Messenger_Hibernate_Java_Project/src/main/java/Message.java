import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time")
    private Date time;

    @Column(name = "text")
    private String text;

    @Column(name = "have_file")
    private boolean haveFile;

}
