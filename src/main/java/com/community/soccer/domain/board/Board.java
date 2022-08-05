package com.community.soccer.domain.board;

import com.community.soccer.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Setter
    private String title;
    @Setter @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
        createDate = LocalDateTime.now();
    }

    //생성 로직
    public static Board createBoard(String title, String content, Member member) {
        return new Board(title, content, member);
    }

    //update
    public void update(String title, String content) {
        this.setTitle(title);
        this.setContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Board board = (Board) o;
        return id != null && Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
