package com.lab.community.domain.like;

import com.lab.community.domain.BaseEntity;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity(name = "LIKE_MST")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long likeId;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userId")
    private User user;

    public static Like from(Board board, User user){
        return Like.builder()
                .board(board)
                .user(user)
                .build();
    }

    public boolean hasLiked(User user){
        return user.getUserId().equals(this.user.getUserId());
    }

}
