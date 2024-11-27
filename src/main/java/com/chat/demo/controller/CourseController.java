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

    @PostMapping
    public Response<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        if (createdCourse == null) {
            return Response.error("Failed to create course");
        }
        return Response.success(createdCourse);
    }

    @GetMapping
    public Response<List<CourseDto>> getAllCourses() {
        return Response.success(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public Response<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = courseService.getCourseById(id);
        if (course == null) {
            return Response.error("Course not found");
        }
        return Response.success(course);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (!deleted) {
            return Response.error("Course not found or could not be deleted");
        }
        return Response.success(null);
    }

    @PutMapping("/{id}")
    public Response<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
        if (updatedCourse == null) {
            return Response.error("Failed to update course");
        }
        return Response.success(updatedCourse);
    }
}
