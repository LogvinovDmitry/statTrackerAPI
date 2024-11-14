package logvinov.testTask.userRestApp.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByDate {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    public SalesByDate salesByDate;
    public TrafficByDate trafficByDate;
}