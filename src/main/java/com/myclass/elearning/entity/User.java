package com.myclass.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotBlank(message = "Tên không được trống")
    private String fullname;
    @Length(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private String website;
    private String facebook;

    @Column(name = "role_id")
    private int roleId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false, insertable = false)
    private Role role;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
