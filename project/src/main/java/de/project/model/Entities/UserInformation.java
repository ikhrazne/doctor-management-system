package de.project.model.Entities;


import de.project.DTOs.UserInformationDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "UserInformation")
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInformationId;

    @Column
    private int weight;

    @Column
    private int height;

    @Column
    private String allergien;

    @OneToOne(mappedBy = "userInformation")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    protected UserInformation() {}

    public UserInformation(int weight, int height, String allergien) {
        this.weight = weight;
        this.height = height;
        this.allergien = allergien;
    }

    public Long getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(Long userInformationId) {
        this.userInformationId = userInformationId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAllergien() {
        return allergien;
    }

    public void setAllergien(String allergien) {
        this.allergien = allergien;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserInformationDTO convertToDTO() {
        return new UserInformationDTO(this.weight, this.height, this.allergien);
    }
}
