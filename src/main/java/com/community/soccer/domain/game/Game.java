package com.community.soccer.domain.game;

import com.community.soccer.domain.item.PositionDto;
import com.community.soccer.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {

    @Id @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String place;
    @Setter @NotBlank
    private String region;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter @NotNull @Future
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter @NotNull @Future
    private LocalDateTime endDate;

    @PositiveOrZero
    private Integer masterFW;
    @PositiveOrZero
    private Integer masterMF;
    @PositiveOrZero
    private Integer masterDF;
    @PositiveOrZero
    private Integer masterGK;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "game")
    private final List<Player> players = new ArrayList<>();

    private Game(String title, String place, String region,
                 LocalDateTime startDate, LocalDateTime endDate, String description,
                 PositionDto positionDto) {
        this.title = title;
        this.place = place;
        this.region = region;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

        this.masterFW = positionDto.masterFW();
        this.masterMF = positionDto.masterMF();
        this.masterDF = positionDto.masterDF();
        this.masterGK = positionDto.masterGK();
    }

    //정보 갱신 메소드
    public void update(LocalDateTime startDate, LocalDateTime endDate, String region) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setRegion(region);
    }

    //생성 메소드
    public static Game createGame(String title, String place, String region,
                                  LocalDateTime startDate, LocalDateTime endDate,
                                  String description, PositionDto positionDto) {
        return new Game(title, place, region, startDate, endDate, description, positionDto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
