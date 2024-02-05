package dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestCase {
    String title;
    String status;
    String description;
    @SerializedName("suite_id")
    Integer suiteId;
    String severity;
    String priority;
    String type;
    String layer;
    @SerializedName("is_flaky")
    String isFlaky;
    String behavior;
    String automation;
    String preconditions;
    String postconditions;

}
