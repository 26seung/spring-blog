package com.one.blog.controller.api;

import com.one.blog.domain.Board;
import com.one.blog.domain.Reply;
import com.one.blog.dto.ReplySaveRequestDto;
import com.one.blog.dto.ResponseDto;
import com.one.blog.security.auth.PrincipalDetail;
import com.one.blog.service.BoardService;
import com.one.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


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
    @DeleteMapping("/board/delete/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id){
        boardService.글삭제(id);
            return new ResponseDto<Integer>(HttpStatus.OK, 200);
    }
    @PutMapping("/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board){
        boardService.글수정(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK,200);
    }

    //데이터 받을 때 컨트롤러에서 dto 를 만들어서 받는게 좋다.
    // 사용자 정보는 Front쪽에서 (principal.userId) 식으로 전달
//    @PostMapping ("/board/{boardId}/reply")
//    public ResponseDto<Integer> replySave(@PathVariable Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail){
//
//        boardService.댓글쓰기(reply,boardId, principalDetail.getUser());
//        return new ResponseDto<Integer>(HttpStatus.OK,200);
//    }

    @PostMapping ("/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){

        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK,200);
    }
}
