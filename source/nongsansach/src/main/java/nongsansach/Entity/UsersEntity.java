package nongsansach.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullname;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne
    @JoinColumn(name = "role")
    private RolesEntity role;

    @OneToMany(mappedBy = "usersEntity")
    private List<ReviewsEntity> reviewsEntityList;

    @OneToMany(mappedBy = "usersOrderEntity")
    private List<OrderEntity> usersOrderEntity;

    @ManyToOne
    @JoinColumn(name = "notify_user")
    private Notify_UserEntity notifyUserEntity;

    public List<ReviewsEntity> getReviewsEntityList() {
        return reviewsEntityList;
    }

    public void setReviewsEntityList(List<ReviewsEntity> reviewsEntityList) {
        this.reviewsEntityList = reviewsEntityList;
    }

    public UsersEntity() {

    }

    public UsersEntity(String email, String password, String fullname, RolesEntity role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public UsersEntity(String email, String password, String fullname, RolesEntity role, String address, String phone) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }

    public List<OrderEntity> getUsersOrderEntity() {
        return usersOrderEntity;
    }

    public void setUsersOrderEntity(List<OrderEntity> usersOrderEntity) {
        this.usersOrderEntity = usersOrderEntity;
    }

    public Notify_UserEntity getNotifyUserEntity() {
        return notifyUserEntity;
    }

    public void setNotifyUserEntity(Notify_UserEntity notifyUserEntity) {
        this.notifyUserEntity = notifyUserEntity;
    }
}
