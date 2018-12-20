package hello.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="PAPER")
public class Paper {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="author")
    private String author;

    @Column(name="name")
    private String name;

    @Column(name="text")
    private String text;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content_file")
    private byte[] content_file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getContent_file() {
        return content_file;
    }

    public void setContent_file(byte[] content) {
        this.content_file = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return "id="+id+", name="+name+", author="+author;
    }
}
