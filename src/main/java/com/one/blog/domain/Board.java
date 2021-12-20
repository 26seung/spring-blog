package com.one.blog.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

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
    @Lob    // 대용량 데이터
    private String content;
    private int count;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
    @OrderBy("id desc")
    private List<Reply> replys;
    @CreationTimestamp
    private LocalDateTime createDate;
}
