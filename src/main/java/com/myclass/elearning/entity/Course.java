package com.myclass.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Tiêu đề không được trống")
    private String title;
    @NotBlank(message = "Bạn chưa chọn hình ảnh")
    private String image;
    @Min(value = 2, message = "Số buổi học phải lớn hơn 2")
    private int lecturesCount;
    @Min(value = 12, message = "số giờ học phải lớn hơn 12")
    private int hourCount;
    @Min(value = 10000, message = "Giá khóa học không hợp lệ")
    private double price;
    @Range(min = 0, max = 100, message = "Giảm giá phải từ 0-100%")
    private int discount;

    private double promotionPrice;
    @NotBlank(message = "Mô tả không được trống")
    private String description;
    @NotBlank(message = "Nội dung không được trống")
    private String content;

    private int viewCount;

    private Date lastUpdate;

    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
    private Set<Target> targets;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
    private Set<Video> videos;
}
