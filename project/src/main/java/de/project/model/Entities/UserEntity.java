package de.project.model.Entities;


import de.project.DTOs.User;
import de.project.Enums.Role;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "benutzer")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String password;

    @Column
    private Role role;

    @Column
    private String description;

    @OneToOne
    private AdresseEntity adresse;

    @OneToMany
    private List<AppointmentEntity> appointments;

    @ManyToMany
    @JoinTable(
            name = "user_insurance",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private Set<InsuranceEntity> insurances;

    /* @OneToMany
    private List<Image> images;*/

    @ManyToOne
    private DoctorType doctorType;

    @OneToOne
    private UserInformation userInformation;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_speciality",
            joinColumns = @JoinColumn(name =   "user_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private Set<SpecialityEntity> specialities;

    protected UserEntity() {

    }

    public UserEntity(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String firstName, String lastName, String email, String phoneNumber, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdresseEntity getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseEntity adresse) {
        this.adresse = adresse;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SpecialityEntity> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<SpecialityEntity> specialities) {
        this.specialities = specialities;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

    public Set<InsuranceEntity> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<InsuranceEntity> insurances) {
        this.insurances = insurances;
    }

    public User convertToUser() {
        User user = new User();
        user.setUserId(this.userId);
        user.setFirstname(this.firstName);
        user.setLastname(this.lastName);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        user.setRole(this.role);
        return user;
    }

    public void generateFromUser(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstname();
        this.lastName = user.getLastname();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
