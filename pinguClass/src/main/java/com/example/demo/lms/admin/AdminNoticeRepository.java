package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Notice;

public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {

	@Query(value = "SELECT * FROM notice limit :start, :idx", nativeQuery = true)	
	List<Notice> findNoticeLimitStartIdx(@Param("start") int start, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM notice" , nativeQuery = true)
	int countNoticeByKeyword();

	/* 관리자 기본키로 공지사항 조회 */
	@Query(value = "SELECT count(*) FROM notice" , nativeQuery = true)
	int countNoticeAll();

	// 페이징
	@Query(value = "SELECT * FROM notice limit :start, :idx", nativeQuery = true)
	List<Notice> findNoticeByAdminId(@Param("start") int startNo, @Param("idx") int pageSize);
	
	
	
}
