package org.example.fileuploaddownload.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private Integer count;
    private Integer code;
    private Boolean success;
    private String message;
    private T content;
}
