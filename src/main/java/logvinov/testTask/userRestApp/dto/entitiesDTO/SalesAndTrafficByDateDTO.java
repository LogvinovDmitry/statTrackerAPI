package logvinov.testTask.userRestApp.dto.entitiesDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByDateDTO implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    public SalesByDateDTO salesByDate;
    public TrafficByDateDTO trafficByDate;
}
