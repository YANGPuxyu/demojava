package com.chat.demo.service;

import com.chat.demo.entity.Course;
import com.chat.demo.entity.DTO.CourseDto;
import com.chat.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 将 Course 转换为 CourseDto
    private CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(course, courseDto);  // 使用 BeanUtils 复制属性
        return courseDto;
    }

    // 将 CourseDto 转换为 Course
    private Course convertToEntity(CourseDto courseDto) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);  // 使用 BeanUtils 复制属性
        return course;
    }

    // 创建课程
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = convertToEntity(courseDto);  // 使用手动转换方法
        Course savedCourse = courseRepository.save(course);  // 保存课程
        return convertToDto(savedCourse);  // 使用手动转换方法
    }

    // 获取所有课程
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();  // 获取所有课程
        return courses.stream()
                .map(this::convertToDto)  // 使用手动转换方法
                .collect(Collectors.toList());
    }

    // 根据课程 ID 获取课程
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);  // 根据 ID 查找课程
        return course != null ? convertToDto(course) : null;  // 使用手动转换方法
    }

    // 删除课程
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);  // 删除课程
            return true;
        }
        return false;
    }

    // 更新课程
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElse(null);  // 查找课程
        if (course != null) {
            // 更新课程信息
            course.setName(courseDto.getName());
            course.setDescription(courseDto.getDescription());
            course.setPrice(courseDto.getPrice());
            // 如果课程涉及聊天室等其他字段需要更新，可以在这里设置
            Course updatedCourse = courseRepository.save(course);  // 保存更新后的课程
            return convertToDto(updatedCourse);  // 返回更新后的 DTO
        }
        return null;
    }

    // 获取某个聊天室的所有课程
    public List<CourseDto> getCoursesByChatRoom(Long chatRoomId) {
        List<Course> courses = courseRepository.findByChatRoomId(chatRoomId);  // 根据聊天室 ID 查询课程
        return courses.stream()
                .map(this::convertToDto)  // 使用手动转换方法
                .collect(Collectors.toList());
    }

    // 获取某个教师的所有课程
    public List<CourseDto> getCoursesByTeacher(String teacherName) {
        List<Course> courses = courseRepository.findByTeacherName(teacherName);  // 根据教师姓名查询课程
        return courses.stream()
                .map(this::convertToDto)  // 使用手动转换方法
                .collect(Collectors.toList());
    }
}
