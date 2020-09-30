package com.myclass.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 4, message = "Tiêu đề phải có ít nhất 4 ký tự")
    private String title;
    @NotBlank(message = "Icon không được trống")
    private String icon;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
    private Set<Course> courses;
}
