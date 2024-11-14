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
public class ClaimsAmountDTO implements Serializable {
    public int amount;
    public String currencyCode;
}
