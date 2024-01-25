package com.example.educate.dtos;

import com.example.educate.consts.OnCreate;
import com.example.educate.consts.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @Null(groups = OnCreate.class)
    @NotBlank(groups = OnUpdate.class)
    private String id;

    @NotBlank(groups = OnCreate.class)
    private String name;

    @NotNull(groups = OnCreate.class)
    private MultipartFile content;

    @NotBlank(groups = OnCreate.class)
    private String author;

    private String tags;
}
