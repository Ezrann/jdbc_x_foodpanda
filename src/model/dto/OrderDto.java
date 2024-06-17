package model.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record OrderDto(
        Integer id,
        String customerName,
        String orderName,
        String orderDescription,
        Date orderedAt
) {
}
