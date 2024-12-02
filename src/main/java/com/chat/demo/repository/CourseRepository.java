package com.chat.demo.repository;

import com.chat.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByChatRoomId(Long chatRoomId);

    List<Course> findByTeacherName(String teacherName);
}
