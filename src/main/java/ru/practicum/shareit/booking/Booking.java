package ru.practicum.shareit.booking;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Period;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {
    private Long bookingId;
    @NotNull(message = "Не указан id вещи.")
    private Long itemId;
    @NotNull(message = "Не указан id владельца.")
    private Long owner;
    @NotNull(message = "Не указан период бронирования.")
    private Period leasePeriod;
    private Boolean isApproved;
    private String review;
}