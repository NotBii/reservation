package zerobase.reserve.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ReviewModel {

    private String username;

    private long reservationid;

    private String date;

}
