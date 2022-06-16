package com.lab.community.controller.board;

import com.lab.community.controller.ResponseData;
import com.lab.community.controller.param.board.ModifyBoardParam;
import com.lab.community.controller.param.board.WriteBoardParam;
import com.lab.community.controller.payload.BoardPayload;
import com.lab.community.domain.user.User;
import com.lab.community.manager.board.BoardManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardManager boardManager;

    @GetMapping
    public ResponseData getBoardList(final Pageable pageable){
        List<BoardPayload> result = boardManager.getBoardList(pageable);
        return ResponseData.success(result);
    }


    @PostMapping
    public ResponseData writeBoard(User user,
                                   @RequestBody WriteBoardParam param){
        boardManager.writeBoard(param, user);
        return ResponseData.success();
    }

    @DeleteMapping("/{boardId}")
    public ResponseData deleteBoard(User user,
                                    @PathVariable Long boardId){
        boardManager.deleteBoard(boardId, user);
        return ResponseData.success();
    }

    @PatchMapping
    public ResponseData modifyBoard(User user,
                                    @RequestBody ModifyBoardParam param){
        boardManager.modifyBoard(param, user);
        return ResponseData.success();
    }

}
