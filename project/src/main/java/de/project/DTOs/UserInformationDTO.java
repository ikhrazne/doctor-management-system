package de.project.DTOs;


import de.project.model.Entities.UserInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationDTO {

    private int weight;
    private int height;
    private String allergien;


    public UserInformation convertToUserInformation() {
        return new UserInformation(weight, height, allergien);
    }

}
