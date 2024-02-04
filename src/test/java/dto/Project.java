package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    String title;
    String code;
    String description;
}
