package com.app.responce;

import lombok.Data;

@Data
public class SearchResponce {
    private String name;
    private Long mobile;
    private String email;
    private Character gender;
    private Long ssn;
    private String planName;
    private String planStatus;
}
