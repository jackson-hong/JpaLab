package com.lab.community.controller.payload;

import com.lab.community.common.type.YnType;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.like.Like;
import com.lab.community.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPayload {

    private String title;
    private String content;
    private String authorName;
    private String authorType;
    private Integer likeCount;
    private YnType hasLiked;

    // TODO REFACTORING
    public static BoardPayload from(Board board, List<Like> likes, User user) {
        boolean hasLiked = likes.stream().anyMatch(like -> like.hasLiked(user));
        int likeCount = likes.size();
        return BoardPayload.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .authorName(board.getUser().getNickName())
                .authorType(board.getUser().getAccountType().getName())
                .hasLiked(YnType.fromBoolean(hasLiked))
                .likeCount(likeCount)
                .build();
    }

    public static BoardPayload from(Board board, List<Like> likes) {
        int likeCount = likes.size();
        return BoardPayload.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .authorName(board.getUser().getNickName())
                .authorType(board.getUser().getAccountType().getName())
                .hasLiked(YnType.fromBoolean(false))
                .likeCount(likeCount)
                .build();
    }
}

