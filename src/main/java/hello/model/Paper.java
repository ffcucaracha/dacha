package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAPER")
public class Paper {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="author")
    private String author;

    @Column(name="name")
    private String name;

    @Column(name="text")
    private String text;

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

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return "id="+id+", name="+name+", author="+author;
    }
}
