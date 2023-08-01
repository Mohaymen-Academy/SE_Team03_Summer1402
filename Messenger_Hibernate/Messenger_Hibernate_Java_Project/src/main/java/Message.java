import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Chat_Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message_time")
    private Date time;

    @Column(name = "message_text")
    private String text;

    @Column(name = "have_file")
    private boolean haveFile;

    @Column(name = "fk_file_id")
    private int fileId;

    @Column(name = "fk_sender")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private  int senderId;

    @Column(name = "fk_receiver")
    @ManyToOne(targetEntity = Profile)
    private  int receiverId;

}
