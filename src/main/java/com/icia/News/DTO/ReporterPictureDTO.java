package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReporterPictureDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String fileAttached;
    private Long ReportId;

}
