package logvinov.testTask.userRestApp.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheService {

    @CacheEvict(value = {
            "findByDateInSalesAndTrafficByDate",
            "findByParentAsinInSalesAndTrafficByAsin",
            "findByDateRangeInSalesAndTrafficByDate",
            "findByParentAsinListInSalesAndTrafficByAsin",
            "findAllSalesAndTrafficByAsin",
            "findAllSalesAndTrafficByDate"
    }, allEntries = true)
    public void clearAllCaches() {
        // Очищает все указанные кеши
    }
}
