package com.lab.community.domain.user;

import com.lab.community.common.type.YnType;
import com.lab.community.common.type.user.AccountType;
import com.lab.community.domain.BaseEntity;
import com.lab.community.domain.board.Board;
import com.lab.community.domain.like.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity(name = "USER_MST")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column
    private String nickName;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column
    private String accountId;

    @Column
    @Enumerated(EnumType.STRING)
    private YnType quit;

    @OneToMany(mappedBy = "user", cascade = ALL, fetch = LAZY)
    private List<Board> boardList;

    @OneToMany(mappedBy = "user", cascade = ALL, fetch = LAZY)
    private List<Like> likeList;

    public static User buildAnonymousUser(){
        return User.builder()
                .accountType(AccountType.ANONYMOUS)
                .build();
    }
}
