package com.one.blog.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Lob
    private String content;
    private int count;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;
    @CreationTimestamp
    private LocalDateTime createDate;
}
