package logvinov.testTask.userRestApp.controller;

import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByAsinDTO;
import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByDateDTO;
import logvinov.testTask.userRestApp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/by-date")
    public List<SalesAndTrafficByDateDTO> getByDateInSalesAndTrafficByDate(@RequestParam("date")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Instant instantDate = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        return reportService.getByDateInSalesAndTrafficByDate(instantDate);
    }

    @GetMapping("/by-asin")
    public List<SalesAndTrafficByAsinDTO> getByParentAsinInSalesAndTrafficByAsin(@RequestParam("asin") String asin) {

        return reportService.getByParentAsinInSalesAndTrafficByAsin(asin);
    }

    @GetMapping("/by-date-range")
    public List<SalesAndTrafficByDateDTO> getByDateRangeInSalesAndTrafficByDate(@RequestParam("startDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam("endDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Instant instantStartDate = startDate.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant instantEndDate = endDate.atStartOfDay(ZoneOffset.UTC).toInstant();

        return reportService.getByDateRangeInSalesAndTrafficByDate(instantStartDate, instantEndDate);
    }

    @GetMapping("/by-asin-list")
    public List<SalesAndTrafficByAsinDTO> getByParentAsinListInSalesAndTrafficByAsin(
                                                 @RequestParam("asins") List<String> asins) {

        return reportService.getByParentAsinListInSalesAndTrafficByAsin(asins);
    }

    @GetMapping("/all-by-asin")
    public List<SalesAndTrafficByAsinDTO> getAllSalesAndTrafficByAsin() {

        return reportService.getAllSalesAndTrafficByAsin();
    }

    @GetMapping("/all-by-date")
    public List<SalesAndTrafficByDateDTO> getAllSalesAndTrafficByDate() {

        return reportService.getAllSalesAndTrafficByDate();
    }
}