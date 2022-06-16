package com.lab.community.manager.like;

import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import com.lab.community.common.type.user.AccountType;
import com.lab.community.controller.param.like.WriteLikeParam;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.like.Like;
import com.lab.community.domain.user.User;
import com.lab.community.service.board.BoardService;
import com.lab.community.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeManager {

    private final LikeService likeService;
    private final BoardService boardService;

    public void writeLike(WriteLikeParam param, User user){
        if (user.getAccountType() == AccountType.ANONYMOUS) throw new LabException(ResultCode.RESULT_4003);
        Board board = boardService.findBoard(param.getBoardId());
        Like like = Like.from(board, user);
        likeService.writeLike(like);
    };
}
