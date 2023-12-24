package de.project.model.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;

    @Column
    private String image;

    /* @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; */

    protected Image() {

    }

    public Image(String image) {
        this.image = image;
    }


    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
