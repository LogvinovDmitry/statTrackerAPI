package logvinov.testTask.userRestApp.service.impl;

import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByAsinDTO;
import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByDateDTO;
import logvinov.testTask.userRestApp.model.entities.Report;
import logvinov.testTask.userRestApp.model.entities.SalesAndTrafficByAsin;
import logvinov.testTask.userRestApp.model.entities.SalesAndTrafficByDate;
import logvinov.testTask.userRestApp.repository.ReportRepository;
import logvinov.testTask.userRestApp.service.ReportService;
import logvinov.testTask.userRestApp.util.SalesAndTrafficByAsinConverter;
import logvinov.testTask.userRestApp.util.SalesAndTrafficByDateConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final SalesAndTrafficByAsinConverter salesAndTrafficByAsinConverter;
    private final SalesAndTrafficByDateConverter salesAndTrafficByDateConverter;

    public ReportServiceImpl(ReportRepository reportRepository,
                             SalesAndTrafficByAsinConverter salesAndTrafficByAsinConverter,
                             SalesAndTrafficByDateConverter salesAndTrafficByDateConverter) {
        this.reportRepository = reportRepository;
        this.salesAndTrafficByAsinConverter = salesAndTrafficByAsinConverter;
        this.salesAndTrafficByDateConverter = salesAndTrafficByDateConverter;
    }

    @Override
    @Cacheable(value = "findByDateInSalesAndTrafficByDate")
    public List<SalesAndTrafficByDateDTO> getByDateInSalesAndTrafficByDate(Instant date) {
        Report report = reportRepository.findByDateInSalesAndTrafficByDate(date)
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the given DATE"));

        ArrayList<SalesAndTrafficByDate> salesAndTrafficByDateList = report.getSalesAndTrafficByDate();

        return salesAndTrafficByDateList.stream()
                .map(salesAndTrafficByDate ->
                        salesAndTrafficByDateConverter.convertToSalesAndTrafficByDateDTO(salesAndTrafficByDate))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "findByParentAsinInSalesAndTrafficByAsin")
    public List<SalesAndTrafficByAsinDTO> getByParentAsinInSalesAndTrafficByAsin(String parentAsin) {

        Report report = reportRepository.findByParentAsinInSalesAndTrafficByAsin(parentAsin)
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the given ASIN"));

        ArrayList<SalesAndTrafficByAsin> salesAndTrafficByAsinList = report.getSalesAndTrafficByAsin();

        return salesAndTrafficByAsinList.stream()
                .map(salesAndTrafficByAsin ->
                        salesAndTrafficByAsinConverter.convertToSalesAndTrafficByAsinDTO(salesAndTrafficByAsin))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "findByDateRangeInSalesAndTrafficByDate")
    public List<SalesAndTrafficByDateDTO> getByDateRangeInSalesAndTrafficByDate(Instant startDate, Instant endDate) {
        Report report = reportRepository.findByDateRangeInSalesAndTrafficByDate(startDate, endDate)
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the given DATES"));

        ArrayList<SalesAndTrafficByDate> salesAndTrafficByDateList = report.getSalesAndTrafficByDate();

        return salesAndTrafficByDateList.stream()
                .map(salesAndTrafficByDate ->
                        salesAndTrafficByDateConverter.convertToSalesAndTrafficByDateDTO(salesAndTrafficByDate))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "findByParentAsinListInSalesAndTrafficByAsin")
    public List<SalesAndTrafficByAsinDTO> getByParentAsinListInSalesAndTrafficByAsin(List<String> parentAsins) {
        Report report = reportRepository.findByParentAsinListInSalesAndTrafficByAsin(parentAsins)
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the given ASINS"));

        ArrayList<SalesAndTrafficByAsin> salesAndTrafficByAsinList = report.getSalesAndTrafficByAsin();

        return salesAndTrafficByAsinList.stream()
                .map(salesAndTrafficByAsin ->
                        salesAndTrafficByAsinConverter.convertToSalesAndTrafficByAsinDTO(salesAndTrafficByAsin))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "findAllSalesAndTrafficByAsin")
    public List<SalesAndTrafficByAsinDTO> getAllSalesAndTrafficByAsin() {

        Report report = reportRepository.findAllSalesAndTrafficByAsin()
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the ASIN"));

        ArrayList<SalesAndTrafficByAsin> salesAndTrafficByAsinList = report.getSalesAndTrafficByAsin();

        return salesAndTrafficByAsinList.stream()
                .map(salesAndTrafficByAsin ->
                        salesAndTrafficByAsinConverter.convertToSalesAndTrafficByAsinDTO(salesAndTrafficByAsin))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "findAllSalesAndTrafficByDate")
    public List<SalesAndTrafficByDateDTO> getAllSalesAndTrafficByDate() {

        Report report = reportRepository.findAllSalesAndTrafficByDate()
                .orElseThrow(() -> new RuntimeException("No sales and traffic data found for the DATES"));

        ArrayList<SalesAndTrafficByDate> salesAndTrafficByDateList = report.getSalesAndTrafficByDate();

        return salesAndTrafficByDateList.stream()
                .map(salesAndTrafficByDate ->
                        salesAndTrafficByDateConverter.convertToSalesAndTrafficByDateDTO(salesAndTrafficByDate))
                .collect(Collectors.toList());
    }
}