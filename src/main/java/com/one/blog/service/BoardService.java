package com.one.blog.service;

import com.one.blog.domain.Board;
import com.one.blog.domain.Reply;
import com.one.blog.domain.User;
import com.one.blog.dto.ReplySaveRequestDto;
import com.one.blog.repository.BoardRepository;
import com.one.blog.repository.ReplyRepository;
import com.one.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board 상세보기(long id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을수 없습니다.");
        });
    }
    @Transactional
    public void 글삭제(Long id){
        boardRepository.deleteById(id);
    }
    @Transactional
    public void 글수정(Long id, Board requestBoard){
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 수정하기 실패 : 아이디를 찾을수 없습니다.");
        });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    };

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

        // 네이티브 쿼리 사용
         replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
//        System.out.println(reply);
        // b안. 영속화 방식
        // 이러한 영속화 과정이 귀찮다면 네이티브쿼리를 사용하여 진행이 가능하다
//        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//            return new IllegalArgumentException("글 찾기 실패 : 유저 ID 를 찾을수 없습니다");
//        });
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//            return new IllegalArgumentException("글 찾기 실패 : 게시글 ID 를 찾을수 없습니다");
//        });
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();
//
//        replyRepository.save(reply);

        // c안. DTO 내부 메서드를 만들어 사용

//        Reply reply = new Reply();
//        reply.update(user, board, replySaveRequestDto.getContent());


    }

}
