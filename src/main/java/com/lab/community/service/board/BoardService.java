package com.lab.community.service.board;

import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.board.BoardQueryRepository;
import com.lab.community.domain.board.BoardRepository;
import com.lab.community.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    public void save(Board board){
        boardRepository.save(board);
    }

    public Board findBoard(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(() -> new LabException(ResultCode.RESULT_5002));
    }

    public Board findBoardByIdAndUser(Long boardId, User user){
        return boardRepository.findByBoardIdAndUser(boardId, user).orElseThrow(() -> new LabException(ResultCode.RESULT_5002));
    }

    @Transactional
    public void softDeleteBoard(Board entity){
        entity.delete();
    }

    public Page<Board> findAllBoards(Pageable pageable){
        return boardQueryRepository.findAllBoards(pageable);
    }
}
