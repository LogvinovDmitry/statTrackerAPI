package logvinov.testTask.userRestApp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficByAsin {
    public int browserSessions;
    public int browserSessionsB2B;
    public int mobileAppSessions;
    public int mobileAppSessionsB2B;
    public int sessions;
    public int sessionsB2B;
    public double browserSessionPercentage;
    public double browserSessionPercentageB2B;
    public double mobileAppSessionPercentage;
    public double mobileAppSessionPercentageB2B;
    public double sessionPercentage;
    public double sessionPercentageB2B;
    public int browserPageViews;
    public int browserPageViewsB2B;
    public int mobileAppPageViews;
    public int mobileAppPageViewsB2B;
    public int pageViews;
    public int pageViewsB2B;
    public double browserPageViewsPercentage;
    public double browserPageViewsPercentageB2B;
    public double mobileAppPageViewsPercentage;
    public double mobileAppPageViewsPercentageB2B;
    public double pageViewsPercentage;
    public double pageViewsPercentageB2B;
    public double buyBoxPercentage;
    public double buyBoxPercentageB2B;
    public double unitSessionPercentage;
    public double unitSessionPercentageB2B;
}
