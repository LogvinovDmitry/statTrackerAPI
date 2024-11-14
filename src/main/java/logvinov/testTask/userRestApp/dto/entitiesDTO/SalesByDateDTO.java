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
public class SalesByDateDTO implements Serializable {
    public OrderedProductSalesDTO orderedProductSales;
    public OrderedProductSalesB2BDTO orderedProductSalesB2B;
    public int unitsOrdered;
    public int unitsOrderedB2B;
    public int totalOrderItems;
    public int totalOrderItemsB2B;
    public AverageSalesPerOrderItemDTO averageSalesPerOrderItem;
    public AverageSalesPerOrderItemB2BDTO averageSalesPerOrderItemB2B;
    public double averageUnitsPerOrderItem;
    public double averageUnitsPerOrderItemB2B;
    public AverageSellingPriceDTO averageSellingPrice;
    public AverageSellingPriceB2BDTO averageSellingPriceB2B;
    public int unitsRefunded;
    public double refundRate;
    public int claimsGranted;
    public ClaimsAmountDTO claimsAmount;
    public ShippedProductSalesDTO shippedProductSales;
    public int unitsShipped;
    public int ordersShipped;
}
