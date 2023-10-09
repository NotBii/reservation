package zerobase.reserve.model;

import lombok.Builder;
import lombok.Data;
import zerobase.reserve.persist.entity.ReservationEntity;

@Data
@Builder

public class ReservationModel {
    private Long id;
    private String username;

    private String storename;

    private String reservedate;

    private String status;

    private String date;

    private String description;


    public ReservationEntity toEntity() {
        return ReservationEntity.builder()
                .id(this.id)
                .username(this.username)
                .storename(this.storename)
                .reservedate(this.reservedate)
                .status(this.status)
                .date(this.date)
                .description(this.description)
                .build();
    }
}
