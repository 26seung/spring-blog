package com.one.blog.repository;

import com.one.blog.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 네이티브 쿼리 사용  (ReplySaveRequestDto 에서 받아오는 userId, boardId, content 순서대로)
    // 사용시 영속화할 필요가 없다.,
    @Modifying
    @Query(value = "INSERT INTO Reply(userId, boardId, content, createDate) VALUE(?1,?2,?3,now())",nativeQuery = true)
    void mSave(Long userId,Long boardID, String content);
    // 인서트, 업데이트, 딜리트 시 업데이트 된 행의 개수를 리턴해줌
}
