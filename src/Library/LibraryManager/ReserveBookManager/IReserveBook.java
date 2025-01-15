package Library.LibraryManager.ReserveBookManager;

import Library.Entities.ReservationDetail.ReservationDetail;
import Library.ErrorManagement.ReserveException;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

public interface IReserveBook {
    void reserveBook(String memberID, String bookID, LocalDate reservationDate) throws ReserveException;
    boolean isCheckingDetail(String bookID);
    Queue<ReservationDetail> getInformationReservation(String BookID) throws ReserveException;
    void remove(String memberID, String bookID, LocalDate reservationDate) throws ReserveException;
    List<ReservationDetail> getAll();
    void update(String bookID, ReservationDetail updatedReservation) throws ReserveException;
}

