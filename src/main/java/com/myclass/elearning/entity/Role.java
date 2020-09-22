package com.myclass.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 4, message = "Tên quyền phải có ít nhất 4 ký tự")
    private String name;
    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;
}
