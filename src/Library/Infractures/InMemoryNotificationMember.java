package Library.Infractures;

import Library.Entities.NotificationDetail.NotificationMember;
import Library.ErrorManagement.NotificationException;
import Library.UseCasesForLibrary.UseCases_NotificationMember.INotificationMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryNotificationMember implements INotificationMember {
    // Storage for notifications
    private List<NotificationMember> notifications;

    // Constructor to initialize storage
    public InMemoryNotificationMember() {
        this.notifications = new ArrayList<>();
    }

    @Override
    public void addNewInformation(String memberID, String context, LocalDate notificationDate) {
        // Add a new notification into the list
        NotificationMember newNotification = new NotificationMember(memberID, context, notificationDate);
        notifications.add(newNotification);
    }

    @Override
    public void remove(NotificationMember notificationMember) {
        // Remove the specified notification from the list
        notifications.remove(notificationMember);
    }

    @Override
    public void update(NotificationMember updateObj) throws NotificationException {
        // Find the notification and update it
        boolean isNotFound = true;
        for (int i = 0; i < notifications.size(); i++) {
            NotificationMember existingNotification = notifications.get(i);
            if (existingNotification.getMemberID().equals(updateObj.getMemberID()) &&
                    existingNotification.getNotificationDate().isEqual(updateObj.getNotificationDate())) {
                // Update the notification
                notifications.set(i, updateObj);
                isNotFound = false;
                break;
            }
        }
        if (isNotFound) {
            throw new NotificationException("Notification with the specified member ID and date not found.");
        }
    }

    @Override
    public NotificationMember getNotificationMemberByMemberID(String memberID, LocalDate notificationDate) {
        // Find the notification based on member ID and notification date
        Optional<NotificationMember> filteredNotification = notifications.stream()
                .filter(notification -> notification.getMemberID().equals(memberID) &&
                        notification.getNotificationDate().isEqual(notificationDate))
                .findFirst();

        return filteredNotification.orElse(null);
    }

    @Override
    public void getAll() {
        // Get and display all notifications
        notifications.forEach(notification ->
                System.out.println("MemberID: " + notification.getMemberID() + " - Context: " + notification.getContext() +
                        " - Date: " + notification.getNotificationDate()));
    }

    private boolean isExistingNotification(String memberID, LocalDate date) {
        NotificationMember existingNotification = getNotificationMemberByMemberID(memberID, date);
        return existingNotification != null;
    }
}
