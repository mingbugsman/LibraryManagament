package Library.UseCasesForLibrary.UseCases_NotificationMember;


import Library.Entities.NotificationDetail.NotificationMember;
import Library.ErrorManagement.NotificationException;

import java.time.LocalDate;

public interface INotificationMember {
    void addNewInformation(String memberID, String Context, LocalDate notificationDate);
    void remove(NotificationMember notificationMember);
    void update(NotificationMember updateObj) throws NotificationException;
    NotificationMember getNotificationMemberByMemberID(String memberID, LocalDate date);
    void getAll();
}
