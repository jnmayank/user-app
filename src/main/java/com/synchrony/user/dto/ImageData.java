package com.synchrony.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageData {
    private String id;
    private String title;
    private String description;
    private String type;
    private String deleteHash;
    private String link;
}
