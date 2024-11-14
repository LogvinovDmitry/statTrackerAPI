package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByAsin {
    public String parentAsin;
    public SalesByAsin salesByAsin;
    public TrafficByAsin trafficByAsin;
}
