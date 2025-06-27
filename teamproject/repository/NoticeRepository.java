package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.NoticeTargetAudience;
import com.example.back404.teamproject.entity.Notice;
import com.example.back404.teamproject.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
    List<Notice> findBySchoolOrderByCreatedAtDesc(School school);
    
    @Query("SELECT n FROM Notice n WHERE n.school = :school AND " +
           "(:targetAudience IS NULL OR n.targetAudience = :targetAudience OR n.targetAudience = 'ALL') " +
           "AND n.startDate <= :currentDate AND n.endDate >= :currentDate " +
           "ORDER BY n.createdAt DESC")
    List<Notice> findActiveNotices(@Param("school") School school, 
                                  @Param("targetAudience") NoticeTargetAudience targetAudience,
                                  @Param("currentDate") LocalDate currentDate);
}