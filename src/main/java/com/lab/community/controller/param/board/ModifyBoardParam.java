package com.lab.community.controller.param.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyBoardParam {

    private String boardId;
    private String title;
    private String content;

    public Long getBoardId() {
        return Long.getLong(this.boardId);
    }
}
