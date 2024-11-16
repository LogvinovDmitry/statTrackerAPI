package logvinov.testTask.userRestApp.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import logvinov.testTask.userRestApp.model.entities.Report;
import logvinov.testTask.userRestApp.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class MongoInitializer implements CommandLineRunner {
    @Value("${report.id.default}")
    private String defaultReportId;

    @Autowired
    private ReportRepository mongoRepository;
    @Qualifier("gridFsTemplate")
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CacheService cacheService;
    private long lastModifiedTime = 0;

    @Override
    public void run(String... args) {
        updateDatabase();
    }

    @Scheduled(fixedRate = 4000)
    public void updateDatabase() {
        Resource resource = resourceLoader.getResource("file:./test_report.json");

        log.info("Заходим сюда по расписанию");

        try (InputStream inputStream = resource.getInputStream()) {

            File file = resource.getFile();
            long currentModifiedTime = file.lastModified();

            if (currentModifiedTime > lastModifiedTime) {
                log.info("Заходим сюда, если файл изменился");
                JsonNode jsonNode = objectMapper.readTree(inputStream);
                Report report = objectMapper.treeToValue(jsonNode, Report.class);

                report.setId(defaultReportId);

                mongoRepository.save(report);

                cacheService.clearAllCaches();

                lastModifiedTime = currentModifiedTime;
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + resource.getFilename(), e);
        }
    }
}