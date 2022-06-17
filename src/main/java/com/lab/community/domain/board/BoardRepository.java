package com.lab.community.domain.board;

import com.lab.community.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardIdAndUser(Long boardId, User user);

    @EntityGraph("post-entity-graph-with-like-users")
    List<Board> findAll();
}
