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
public class TrafficByDateDTO implements Serializable {
    public int browserPageViews;
    public int browserPageViewsB2B;
    public int mobileAppPageViews;
    public int mobileAppPageViewsB2B;
    public int pageViews;
    public int pageViewsB2B;
    public int browserSessions;
    public int browserSessionsB2B;
    public int mobileAppSessions;
    public int mobileAppSessionsB2B;
    public int sessions;
    public int sessionsB2B;
    public double buyBoxPercentage;
    public double buyBoxPercentageB2B;
    public double orderItemSessionPercentage;
    public double orderItemSessionPercentageB2B;
    public double unitSessionPercentage;
    public double unitSessionPercentageB2B;
    public int averageOfferCount;
    public int averageParentItems;
    public int feedbackReceived;
    public int negativeFeedbackReceived;
    public int receivedNegativeFeedbackRate;
}
