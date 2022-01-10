package com.one.blog.service;

import com.one.blog.domain.Board;
import com.one.blog.domain.User;
import com.one.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

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

}
