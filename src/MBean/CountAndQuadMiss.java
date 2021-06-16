package MBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.ArrayList;

public class CountAndQuadMiss extends NotificationBroadcasterSupport implements CountAndQuadMissMBean {
    private int countAll = 0;
    private int countUnsuccessful = 0;
    private int notificationCounter = 0;

    @Override
    public int getAllPoints() {
        return countAll;
    }

    @Override
    public int getUnsuccessfulPoints() {
        return countUnsuccessful;
    }

    @Override
    public void update(ArrayList<String> history) {
        countAll = history.size();

        if (history.get(countAll-1).equals("unsuccessful hit")|history.get(countAll-1).equals("not in area")) countUnsuccessful++;

        if (countAll>3)
            if (history.get(countAll-1).equals("unsuccessful hit") & history.get(countAll-2).equals("unsuccessful hit")
                    & history.get(countAll-3).equals("unsuccessful hit") & history.get(countAll-4).equals("unsuccessful hit")) ThrowNotification();
    }

    @Override
    public boolean ThrowNotification() {
        Notification notification = new Notification(
                "QuadMiss", this, notificationCounter++, System.currentTimeMillis(),"You missed four times in a row. What a fool!"
        );
        this.sendNotification(notification);
        return false;
    }
}
