package com.app.binding;

import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanCategoryBinding {
    private Integer planCategoryId;
    private String categoryName;
    private String activeSw;
    private String createdBy;
    private String updatedBy;
    private LocalDate createDate;
    private LocalDate updateDate;
}
