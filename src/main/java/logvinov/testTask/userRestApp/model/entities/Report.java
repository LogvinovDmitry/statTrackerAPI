package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Document(collection = "reports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    private String id;
    public ReportSpecification reportSpecification;
    public ArrayList<SalesAndTrafficByDate> salesAndTrafficByDate;
    public ArrayList<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
