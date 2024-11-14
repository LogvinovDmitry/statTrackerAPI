package logvinov.testTask.userRestApp.dto.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByAsinDTO implements Serializable {
    public String parentAsin;
    public SalesByAsinDTO salesByAsin;
    public TrafficByAsinDTO trafficByAsin;
}
