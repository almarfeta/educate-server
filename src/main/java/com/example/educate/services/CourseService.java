package com.example.educate.services;

import com.example.educate.dtos.CourseRequest;
import com.example.educate.dtos.CourseResponse;
import com.example.educate.entities.Course;
import com.example.educate.exceptions.NotFoundException;
import com.example.educate.repositories.CourseRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final GridFsTemplate gridFsTemplate;

    public void addCourse(CourseRequest courseRequest) {
        MultipartFile file = courseRequest.getContent();
        String fileId;
        try {
            fileId = String.valueOf(gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType()));
        } catch (IOException e) {
            throw new IllegalStateException("Error trying to add the file");
        }
        courseRepository.save(new Course(
                courseRequest.getName(),
                fileId,
                courseRequest.getAuthor(),
                courseRequest.getTags(),
                LocalDateTime.now()
        ));
    }

    public byte[] getCourseContent(String fileId) {
        GridFSFile file = gridFsTemplate.findOne(query(where("_id").is(new ObjectId(fileId))));
        if (file != null) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                gridFsTemplate.getResource(file).getInputStream().transferTo(outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                throw new IllegalStateException("Error trying to get the file");
            }
        } else {
            throw new NotFoundException("File not found");
        }
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(course -> new CourseResponse(course, getCourseContent(course.getContentFileId())))
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(String id) {
        return courseRepository.findById(id)
                .map(course -> new CourseResponse(course, getCourseContent(course.getContentFileId())))
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public void updateCourse(CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseRequest.getId())
                .orElseThrow(() -> new NotFoundException("Course not found"));
        String oldContentFileId = course.getContentFileId();
        if (courseRequest.getName() != null &&
                !courseRequest.getName().isEmpty() &&
                !courseRequest.getName().equals(course.getName())) {
            course.setName(courseRequest.getName());
        }
        if (courseRequest.getAuthor() != null &&
                !courseRequest.getAuthor().isEmpty() &&
                !courseRequest.getAuthor().equals(course.getAuthor())) {
            course.setAuthor(courseRequest.getAuthor());
        }
        if (courseRequest.getTags() != null &&
                !courseRequest.getTags().isEmpty() &&
                !courseRequest.getTags().equals(course.getTags())) {
            course.setTags(courseRequest.getTags());
        }
        if (courseRequest.getContent() != null) {
            String fileId;
            try {
                fileId = String.valueOf(gridFsTemplate.store(courseRequest.getContent().getInputStream(),
                        courseRequest.getContent().getOriginalFilename(),
                        courseRequest.getContent().getContentType()));
            } catch (IOException e) {
                throw new IllegalStateException("Error trying to update the file");
            }
            course.setContentFileId(fileId);
            if (oldContentFileId != null) {
                gridFsTemplate.delete(query(where("_id").is(new ObjectId(oldContentFileId))));
            }
        }
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));
        String contentFileId = course.getContentFileId();
        courseRepository.deleteById(id);
        if (contentFileId != null) {
            gridFsTemplate.delete(query(where("_id").is(new ObjectId(contentFileId))));
        }
    }
}
