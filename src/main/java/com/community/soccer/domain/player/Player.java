package com.community.soccer.domain.player;

import com.community.soccer.domain.game.Game;
import com.community.soccer.domain.item.Position;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {

    @Id @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    @JoinColumn(name = "game_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Pattern(regexp = "^0\\d{1,2}(-\\d{3,4}-\\d{4}|\\d{7,8})$")
    private String phone;

    private Player(Position position, String phone) {
        this.position = position;
        this.phone = phone;
    }

    //생성 로직
    public static Player createPlayer(Position position, String phone) {
        return new Player(position, phone);
    }

    //연관 관계 편의 메소드
    public void setGame(Game game) {
        this.game = game;
        game.getPlayers().add(this);
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
