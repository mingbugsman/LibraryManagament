package Library.LibraryManager.ReserveBookManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.Entities.ReservationDetail.ReservationDetail;
import Library.ErrorManagement.ReserveException;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.time.LocalDate;
import java.util.Queue;


public class ReverseManager {
    private IReserveBook _ReserveBook;
    private ManagerMember managerMember;
    public ReverseManager(IReserveBook reserveBook, ManagerMember managerMember) {
        _ReserveBook = reserveBook;
        this.managerMember = managerMember;
    }

    public void reserveBook(String memberID, String bookID, LocalDate reservationDate) throws ReserveException {
        _ReserveBook.reserveBook(memberID, bookID, reservationDate);
    }

    public boolean isCheckingDetail(String bookID) {
        return _ReserveBook.isCheckingDetail(bookID);
    }
    public Queue<ReservationDetail> getInformationReservation(String bookID) throws ReserveException {
        return _ReserveBook.getInformationReservation(bookID);
    }

    public void notifyAllReservationUsersWithBookID(String contextNotification, String bookID) throws ReserveException {

        Queue<ReservationDetail> infos = getInformationReservation(bookID);

        if (infos == null || infos.isEmpty()) {
            throw new ReserveException("No reservations found for book ID: " + bookID);
        }

        // traversal all infos and notify message for user
        for (ReservationDetail infoMember : infos) {
            String memberID = infoMember.getMemberID();

            // Tìm thành viên theo memberID
            Member foundMember = managerMember.getMember(memberID);

            if (foundMember == null) {
                System.out.println("Member not found for ID: " + memberID);
                continue; // Skip this member if not found
            }

            // Gửi thông báo tới thành viên
            foundMember.getNotification(contextNotification);
        }
    }

}
