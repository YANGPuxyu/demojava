package com.chat.demo.service;

import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.Course;
import com.chat.demo.entity.DTO.CourseDto;
import com.chat.demo.repository.ChatRoomRepository;
import com.chat.demo.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CourseDto getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.map(this::convertToDto).orElse(null);
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = convertToEntity(courseDto);
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(courseDto.getName() + " Chat Room");
        course.setChatRoom(chatRoom);
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        return convertToDto(courseRepository.save(course));
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            return null;
        }
        Course course = courseOptional.get();
        BeanUtils.copyProperties(courseDto, course, "id", "createdAt", "chatRoom");
        course.setUpdatedAt(LocalDateTime.now());
        return convertToDto(courseRepository.save(course));
    }

    private CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(course, courseDto);
        courseDto.setChatRoomId(course.getChatRoom().getId());
        return courseDto;
    }

    private Course convertToEntity(CourseDto courseDto) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        return course;
    }
}
