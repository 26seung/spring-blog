package com.one.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    @Size(min = 3, message = "아이디는 3글자 이상이여야 합니다.")
    private String username;
    @NotBlank
    @Size(max=100)
    private String password;
    @Email
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Erole role;
    @CreationTimestamp
    private Timestamp createDate;

}
