package Library.LibraryManager.ReserveBookManager;

import Library.Entities.Book.Book;

import Library.Entities.Member.Member;
import Library.Entities.ReservationDetail.ReservationDetail;
import Library.ErrorManagement.ReserveException;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.time.LocalDate;
import java.util.*;

public class ReserveBookImp implements  IReserveBook{
    HashMap<String,Queue<ReservationDetail>> reservationsInformation;
    private ManagerBook managerBook;
    private ManagerMember managerMember;
    public ReserveBookImp(ManagerBook managerBook, ManagerMember managerMember) {
        this.reservationsInformation = new HashMap<>();
        this.managerBook = managerBook;
        this.managerMember = managerMember;
    }


    @Override
    public void reserveBook(String memberID, String bookID, LocalDate reservationDate) throws ReserveException {
        Book foundBook = managerBook.getBook(bookID);
        Member foundMember = managerMember.getMember(memberID);
        if (foundBook.isAvailable()) {
            throw new ReserveException("THIS BOOK IS STILL AVAILABLE");
        }

        if (isCheckingDetail(bookID)) {
            if (!isPremiumMember(memberID)) {
                throw new ReserveException("MEMBER WITH ID : " + memberID + " and type is " + foundMember.geTypeMember() + ", doesn't have permission to reserve Book");
            }
        } else {
            // initialize list reservation book if it is not found
            reservationsInformation.put(bookID, new LinkedList<>());
        }
        ReservationDetail infoReservationMember = new ReservationDetail(memberID,reservationDate);
        reservationsInformation.get(bookID).add(infoReservationMember);
    }

    // Update a reservation detail
    public void update(String bookID, ReservationDetail updatedReservation) throws ReserveException {
        if (!reservationsInformation.containsKey(bookID)) {
            throw new ReserveException("No reservations found for Book ID: " + bookID);
        }

        Queue<ReservationDetail> reservationQueue = reservationsInformation.get(bookID);
        Optional<ReservationDetail> existingReservation = reservationQueue.stream()
                .filter(r -> r.getMemberID().equals(updatedReservation.getMemberID()) && r.getReservationDate().isEqual(updatedReservation.getReservationDate()))
                .findFirst();

        if (existingReservation.isPresent()) {
            reservationQueue.remove(existingReservation.get());
            reservationQueue.add(updatedReservation);
        } else {
            throw new ReserveException("Reservation not found for Member ID: " + updatedReservation.getMemberID() + " and Book ID: " + bookID);
        }
    }

    // Get all reservations for all books
    public List<ReservationDetail> getAll() {
        List<ReservationDetail> allReservations = new ArrayList<>();
        for (Queue<ReservationDetail> queue : reservationsInformation.values()) {
            allReservations.addAll(queue);
        }
        return allReservations;
    }

    // Remove a reservation for a specific book and member
    public void remove(String bookID, String memberID, LocalDate date) throws ReserveException {
        if (!reservationsInformation.containsKey(bookID)) {
            throw new ReserveException("No reservations found for Book ID: " + bookID);
        }

        Queue<ReservationDetail> reservationQueue = reservationsInformation.get(bookID);
        Optional<ReservationDetail> reservationToRemove = reservationQueue.stream()
                .filter(r -> r.getMemberID().equals(memberID) && r.getReservationDate().isEqual(date))
                .findFirst();

        if (reservationToRemove.isPresent()) {
            reservationQueue.remove(reservationToRemove.get());
            if (reservationQueue.isEmpty()) {
                reservationsInformation.remove(bookID); // Remove the book entry if no reservations remain
            }
        } else {
            throw new ReserveException("Reservation not found for Member ID: " + memberID + " and Book ID: " + bookID);
        }
    }

    @Override
    public boolean isCheckingDetail(String bookID) {
        if (!reservationsInformation.containsKey(bookID)) {
            return false;
        }
        return true;
    }

    @Override
    public Queue<ReservationDetail> getInformationReservation(String bookID) throws ReserveException {
        if (!isCheckingDetail(bookID)) {
            throw new ReserveException("NOT FOUND BOOK ID IN RESERVATION DETAIL");
        }
        return reservationsInformation.get(bookID);
    }

    private boolean isPremiumMember(String memberID) throws ReserveException {
        Member member = managerMember.getMember(memberID);
        if (member == null) {
            throw new ReserveException("NOT FOUND MEMBER ID : " + memberID);
        }
        if (member.geTypeMember() == "premium") {
            return true;
        }
        return false;
    }
}
