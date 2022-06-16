package com.lab.community.service.like;

import com.lab.community.domain.board.Board;
import com.lab.community.domain.like.Like;
import com.lab.community.domain.like.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void writeLike(Like entity){
        likeRepository.save(entity);
    }

    public List<Like> findLikeByBoard(Board board) {
        return likeRepository.findAllByBoard(board);
    }
}
