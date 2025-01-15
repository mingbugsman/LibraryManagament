package Library.UseCasesForLibrary.UseCases_NotificationMember;

import Library.Entities.NotificationDetail.NotificationMember;
import Library.ErrorManagement.NotificationException;

import java.time.LocalDate;

/**
 * NotificationMemberManager handles the business logic related to notifications.
 * This class uses Dependency Injection (DI) to manage notification data through the INotificationMember interface.
 */
public class NotificationMemberManager {

    // Declare the injected service interface for managing notifications
    private INotificationMember notificationMemberService;

    /**
     * Constructor that uses Dependency Injection (DI) to inject the notification service.
     *
     * @param notificationMemberService The service interface used for managing notifications
     */
    public NotificationMemberManager(INotificationMember notificationMemberService) {
        this.notificationMemberService = notificationMemberService;
    }

    /**
     * Adds a new notification for a member.
     * This method delegates the task to the injected notification service.
     *
     * @param memberID The ID of the member to receive the notification
     * @param context The content of the notification
     * @param notificationDate The date when the notification is sent
     */
    public void addNotification(String memberID, String context, LocalDate notificationDate) {
        notificationMemberService.addNewInformation(memberID, context, notificationDate);
    }

    /**
     * Removes an existing notification for a member.
     * This method delegates the removal to the injected notification service.
     *
     * @param notificationMember The notification object to be removed
     */
    public void removeNotification(NotificationMember notificationMember) {
        notificationMemberService.remove(notificationMember);
    }

    /**
     * Updates an existing notification for a member.
     * This method delegates the update to the injected notification service.
     *
     * @param updateObj The notification object with updated information
     */
    public void updateNotification(NotificationMember updateObj) throws NotificationException {
        notificationMemberService.update(updateObj);
    }

    /**
     * Retrieves a specific notification for a member by their member ID and the notification date.
     *
     * @param memberID The ID of the member whose notification is being retrieved
     * @param notificationDate The date of the notification
     * @return The NotificationMember object if found, or null if no notification exists for the given parameters
     */
    public NotificationMember getNotification(String memberID, LocalDate notificationDate) {
        return notificationMemberService.getNotificationMemberByMemberID(memberID, notificationDate);
    }

    /**
     * Retrieves all notifications.
     * This method delegates the task to the injected notification service to get all notifications.
     */
    public void getAllNotifications() {
        notificationMemberService.getAll();
    }
}
