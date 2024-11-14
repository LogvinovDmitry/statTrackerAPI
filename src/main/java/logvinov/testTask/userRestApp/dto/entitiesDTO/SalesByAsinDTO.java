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
public class SalesByAsinDTO implements Serializable {
    public int unitsOrdered;
    public int unitsOrderedB2B;
    public OrderedProductSalesDTO orderedProductSales;
    public OrderedProductSalesB2BDTO orderedProductSalesB2B;
    public int totalOrderItems;
    public int totalOrderItemsB2B;
}
