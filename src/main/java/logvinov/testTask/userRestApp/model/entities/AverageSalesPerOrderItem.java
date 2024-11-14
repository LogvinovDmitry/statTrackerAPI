package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AverageSalesPerOrderItem {
    public double amount;
    public String currencyCode;
}
