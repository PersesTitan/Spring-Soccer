package com.community.soccer.domain.player;

import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {

    @Id @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Position position;

    private Player(Position position) {
        this.position = position;
    }

    //생성 로직
    public static Player createPlayer(Position position) {
        return new Player(position);
    }
}
