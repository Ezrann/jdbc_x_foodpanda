package model.dto;

public record CreateCustomerDto(
        String name,
        String email,
        String password
) {
}
