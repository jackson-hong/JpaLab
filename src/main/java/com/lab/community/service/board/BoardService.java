package com.lab.community.service.board;

import com.lab.community.common.annotation.QueryCounter;
import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.board.BoardQueryRepository;
import com.lab.community.domain.board.BoardRepository;
import com.lab.community.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final static Logger log = LoggerFactory.getLogger(BoardService.class);

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

    @QueryCounter
    public List<Board> findAllBoards(){
        boardQueryRepository.findAllBoardsWithLikesAndUsers();
        return boardRepository.findAll();
    }

    @Transactional
    public void softDeleteBoard(Board entity){
        entity.delete();
    }

    public Page<Board> findAllBoards(Pageable pageable){
        return boardQueryRepository.findAllBoards(pageable);
    }
}
