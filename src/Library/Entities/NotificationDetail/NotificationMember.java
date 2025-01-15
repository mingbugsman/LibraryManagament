package Library.Entities.NotificationDetail;

import java.time.LocalDate;

public class NotificationMember {
    private String memberID;
    private String context;
    private LocalDate notificationDate;

    public NotificationMember(String memberID,String context, LocalDate notificationDate) {
        this.memberID = memberID;
        this.context = context;
        this.notificationDate = notificationDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDate notificationDate) {
        this.notificationDate = notificationDate;
    }
}
