package logvinov.testTask.userRestApp.repository;


import logvinov.testTask.userRestApp.model.entities.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    @Query(value = "{ 'salesAndTrafficByAsin.parentAsin': ?0 }", fields = "{ 'salesAndTrafficByAsin.$': 1 }")
    Optional<Report> findByParentAsinInSalesAndTrafficByAsin(String parentAsin);

    @Query(value = "{ 'salesAndTrafficByDate.date': ?0 }", fields = "{ 'salesAndTrafficByDate.$': 1 }")
    Optional<Report> findByDateInSalesAndTrafficByDate(Instant date);

    @Query(value = "{ 'salesAndTrafficByDate.date': { $gte: ?0, $lte: ?1 } }",
            fields = """
                    { 
                      'salesAndTrafficByDate': {
                        $filter: {
                          input: '$salesAndTrafficByDate',
                          as: 'item',
                          cond: { 
                            $and: [
                              { $gte: ['$$item.date', ?0] },
                              { $lte: ['$$item.date', ?1] }
                            ]
                          }
                        }
                      }
                    }
                    """)
    Optional<Report> findByDateRangeInSalesAndTrafficByDate(Instant startDate, Instant endDate);

    @Query(value = "{ 'salesAndTrafficByAsin.parentAsin': { $in: ?0 } }",
            fields = """
                    { 
                      'salesAndTrafficByAsin': {
                        $filter: {
                          input: '$salesAndTrafficByAsin',
                          as: 'item',
                          cond: { 
                            $in: ['$$item.parentAsin', ?0]
                          }
                        }
                      }
                    }
                    """)
    Optional<Report> findByParentAsinListInSalesAndTrafficByAsin(List<String> parentAsins);

    @Query(value = "{}", fields = "{ 'salesAndTrafficByAsin': 1 }")
    Optional<Report> findAllSalesAndTrafficByAsin();

    @Query(value = "{}", fields = "{ 'salesAndTrafficByDate': 1 }")
    Optional<Report> findAllSalesAndTrafficByDate();
}