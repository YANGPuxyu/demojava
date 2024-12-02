package com.chat.demo.controller;

import com.chat.demo.entity.DTO.CourseDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 创建课程
    @PostMapping
    public Response<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        if (createdCourse == null) {
            return Response.error("Failed to create course");
        }
        return Response.success(createdCourse);
    }

    // 获取所有课程
    @GetMapping
    public Response<List<CourseDto>> getAllCourses() {
        return Response.success(courseService.getAllCourses());
    }

    // 根据课程 ID 获取课程
    @GetMapping("/{id}")
    public Response<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = courseService.getCourseById(id);
        if (course == null) {
            return Response.error("Course not found");
        }
        return Response.success(course);
    }

    // 删除课程
    @DeleteMapping("/{id}")
    public Response<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (!deleted) {
            return Response.error("Course not found or could not be deleted");
        }
        return Response.success(null);
    }

    // 更新课程
    @PutMapping("/{id}")
    public Response<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
        if (updatedCourse == null) {
            return Response.error("Failed to update course");
        }
        return Response.success(updatedCourse);
    }

    // 获取某个聊天室的所有课程
    @GetMapping("/chatroom/{chatRoomId}")
    public Response<List<CourseDto>> getCoursesByChatRoom(@PathVariable Long chatRoomId) {
        List<CourseDto> courses = courseService.getCoursesByChatRoom(chatRoomId);
        if (courses.isEmpty()) {
            return Response.error("No courses found for the given chat room");
        }
        return Response.success(courses);
    }

    // 获取某个教师的所有课程
    @GetMapping("/teacher/{teacherName}")
    public Response<List<CourseDto>> getCoursesByTeacher(@PathVariable String teacherName) {
        List<CourseDto> courses = courseService.getCoursesByTeacher(teacherName);
        if (courses.isEmpty()) {
            return Response.error("No courses found for the given teacher");
        }
        return Response.success(courses);
    }
}
