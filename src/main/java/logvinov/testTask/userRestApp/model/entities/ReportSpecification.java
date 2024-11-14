package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportSpecification {
    public String reportType;
    public ReportOptions reportOptions;
    public String dataStartTime;
    public String dataEndTime;
    public ArrayList<String> marketplaceIds;
}
