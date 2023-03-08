package ru.practicum.shareit.booking;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotNull;
import java.time.Period;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {
    private Long bookingId;
    @NotNull(message = "Не указана вещь.")
    private Item bookingItem;
    @NotNull(message = "Не указан владелец.")
    private User owner;
    @NotNull(message = "Не указан период бронирования.")
    private Period leasePeriod;
    private Boolean isApproved;
    private String review;
}