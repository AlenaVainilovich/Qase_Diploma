package dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestCase {
    String title;
    String status;
    String description;
    String severity;
    String priority;
    String type;
    String layer;
    String isFlaky;
    String behavior;
    String automationStatus;
    String preConditions;
    String postConditions;

}
