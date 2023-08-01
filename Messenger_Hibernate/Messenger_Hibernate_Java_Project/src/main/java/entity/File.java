package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Data_File")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_data")
    private byte[] data;

    public File() {
    }

    public File(byte[] data) {
        this.data = data;
    }

}
