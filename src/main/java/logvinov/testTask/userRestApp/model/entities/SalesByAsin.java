package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesByAsin {
    public int unitsOrdered;
    public int unitsOrderedB2B;
    public OrderedProductSales orderedProductSales;
    public OrderedProductSalesB2B orderedProductSalesB2B;
    public int totalOrderItems;
    public int totalOrderItemsB2B;
}
