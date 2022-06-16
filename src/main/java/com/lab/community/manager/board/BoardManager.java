package com.lab.community.manager.board;

import com.lab.community.common.security.AuthInfo;
import com.lab.community.common.type.user.AccountType;
import com.lab.community.controller.param.board.ModifyBoardParam;
import com.lab.community.controller.param.board.WriteBoardParam;
import com.lab.community.controller.payload.BoardPayload;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.like.Like;
import com.lab.community.domain.user.User;
import com.lab.community.service.board.BoardService;
import com.lab.community.service.like.LikeService;
import com.lab.community.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardManager {

    private final BoardService boardService;
    private final LikeService likeService;
    private final UserService userService;

    public void writeBoard(WriteBoardParam param, User user){
        Board board = Board.from(param, user);
        boardService.save(board);
    }

    public void deleteBoard(Long boardId, User user){
        Board board = boardService.findBoardByIdAndUser(boardId, user);
        boardService.softDeleteBoard(board);
    }

    public void modifyBoard(ModifyBoardParam param, User user){
        boardService.findBoardByIdAndUser(param.getBoardId(), user);
        Board board = Board.from(param, user);
        boardService.save(board);
    }

    public List<BoardPayload> getBoardList(Pageable pageable){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String accountId = ((AuthInfo)auth).getAccountId();
        User user = userService.findByAccountId(accountId);
        Page<Board> boards = boardService.findAllBoards(pageable);
        return buildBoardListPayload(user, boards);
    }

    private List<BoardPayload> buildBoardListPayload(User user, Page<Board> boards) {
        return boards.getContent().stream().map(board -> {
            List<Like> likes = likeService.findLikeByBoard(board);
            // TODO 핸들러로 분리
            if(user.getAccountType() == AccountType.ANONYMOUS) return BoardPayload.from(board, likes);
            return BoardPayload.from(board, likes, user);
        }).collect(Collectors.toList());
    }

}
