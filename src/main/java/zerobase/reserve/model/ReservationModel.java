package zerobase.reserve.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ReservationModel {
    private String username;

    private String storename;

    private String reservedate;

    private String status;

    private String date;

    private String description;

}
