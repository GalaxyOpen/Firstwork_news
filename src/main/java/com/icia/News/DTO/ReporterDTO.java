package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString

public class ReporterDTO {
    private Long id;
    private int reporterLevel;
    private String reporterEmail;
    private String reporterPassword;
    private String reporterMobile;
    private List<MultipartFile> reporterPicture;
    private int fileAttached;
}
