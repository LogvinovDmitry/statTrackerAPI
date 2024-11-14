package logvinov.testTask.userRestApp.service;

import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByAsinDTO;
import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByDateDTO;

import java.time.Instant;
import java.util.List;

public interface ReportService {

    List<SalesAndTrafficByAsinDTO> getByParentAsinInSalesAndTrafficByAsin(String parentAsin);

    List<SalesAndTrafficByDateDTO> getByDateInSalesAndTrafficByDate(Instant date);

    List<SalesAndTrafficByDateDTO> getByDateRangeInSalesAndTrafficByDate(Instant startDate, Instant endDate);

    List<SalesAndTrafficByAsinDTO> getByParentAsinListInSalesAndTrafficByAsin(List<String> parentAsins);

    List<SalesAndTrafficByAsinDTO> getAllSalesAndTrafficByAsin();

    List<SalesAndTrafficByDateDTO> getAllSalesAndTrafficByDate();
}