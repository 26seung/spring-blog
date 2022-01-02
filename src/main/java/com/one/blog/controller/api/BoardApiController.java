package com.one.blog.controller.api;

import com.one.blog.domain.Board;
import com.one.blog.dto.ResponseDto;
import com.one.blog.security.auth.PrincipalDetail;
import com.one.blog.service.BoardService;
import com.one.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/board/save")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail){
        System.out.println("BoardApiController : save 호출");
        boardService.글쓰기(board,principalDetail.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK,200);
    }
}
