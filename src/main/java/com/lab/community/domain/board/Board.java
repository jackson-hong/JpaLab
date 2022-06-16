package com.lab.community.domain.board;

import com.lab.community.common.type.YnType;
import com.lab.community.controller.param.board.ModifyBoardParam;
import com.lab.community.controller.param.board.WriteBoardParam;
import com.lab.community.domain.BaseEntity;
import com.lab.community.domain.like.Like;
import com.lab.community.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity(name = "BOARD_MST")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@NamedEntityGraph(
        name = "board-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("title"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode("likes")
        }
)
@NamedEntityGraph(
        name = "post-entity-graph-with-like-users",
        attributeNodes = {
                @NamedAttributeNode("title"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "likes", subgraph = "like-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(name = "like-subgraph",
                attributeNodes = {
                        @NamedAttributeNode("user")
                })
        }
)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long boardId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private YnType useYn;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = LAZY, cascade = ALL)
    private List<Like> likes;

    public static Board from(WriteBoardParam param, User user){
        return Board.builder()
                .user(user)
                .title(param.getTitle())
                .content(param.getContent())
                .build();
    }

    public static Board from(ModifyBoardParam param, User user){
        return Board.builder()
                .boardId(param.getBoardId())
                .user(user)
                .title(param.getTitle())
                .content(param.getContent())
                .build();
    }

    public void delete(){
        useYn = YnType.N;
    }
}
