package Library.Entities.ReservationDetail;

import java.time.LocalDate;

public class ReservationDetail {
    private String memberID;
    private LocalDate reservationDate;

    public ReservationDetail(String memberID, LocalDate reservationDate) {
        this.memberID = memberID;
        this.reservationDate = reservationDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
