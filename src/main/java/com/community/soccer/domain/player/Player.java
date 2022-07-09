package com.community.soccer.domain.player;

import com.community.soccer.domain.item.Position;
import com.community.soccer.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Player player = (Player) o;
        return id != null && Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
