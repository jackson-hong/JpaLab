package com.lab.community.controller.param.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteLikeParam {

    private String boardId;

    public Long getBoardId() {
        return Long.getLong(this.boardId);
    }
}
