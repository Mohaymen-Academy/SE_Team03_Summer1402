package database.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import java.util.Date;

@NoArgsConstructor
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
    @ColumnDefault("false")
    private boolean haveFile;

    @ManyToOne
    @JoinColumn(name ="fk_file_id")
    private File fileId;

    @ManyToOne
    @JoinColumn(name ="fk_sender_id")
    private Profile sender;

    @ManyToOne
    @JoinColumn(name ="fk_receiver_id")
    private Profile receiver;

}
