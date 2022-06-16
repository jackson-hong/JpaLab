package com.lab.community.controller.like;

import com.lab.community.controller.ResponseData;
import com.lab.community.controller.param.like.WriteLikeParam;
import com.lab.community.domain.user.User;
import com.lab.community.manager.like.LikeManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    private final LikeManager likeManager;

    @PostMapping
    public ResponseData writeLike(@RequestBody User user,
                                   @RequestBody WriteLikeParam param){
        likeManager.writeLike(param, user);
        return ResponseData.success();
    }
}
