package com.one.blog.repository;

import com.one.blog.domain.Reply;
import com.one.blog.dto.ReplySaveRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 네이티브 쿼리 사용  (ReplySaveRequestDto 에서 받아오는 userId, boardId, content 순서대로)
    // 사용시 영속화할 필요가 없다.,
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUE(?1,?2,?3,now())",nativeQuery = true)
    void mSave(ReplySaveRequestDto replySaveRequestDto);
}
