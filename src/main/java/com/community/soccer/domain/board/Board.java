package com.community.soccer.domain.board;

import com.community.soccer.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Setter private String title;
    @Setter private String content;
    private LocalDateTime createDate;

    @OneToOne
    private Member member;

    private Board(String title, String content) {
        this.title = title;
        this.content = content;
        createDate = LocalDateTime.now();
    }

    //생성 로직
    public static Board createBoard(String title, String content) {
        return new Board(title, content);
    }
}
