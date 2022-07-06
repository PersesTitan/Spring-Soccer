package com.community.soccer.domain.game;

import com.community.soccer.domain.member.Member;
import com.community.soccer.domain.player.Player;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {

    @Id @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    private LocalDateTime gameDate;
    private String region;
    private Integer numberOfPeople; //참가할 인원수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member createMember;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();
}
