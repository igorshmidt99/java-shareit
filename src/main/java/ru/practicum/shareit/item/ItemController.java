package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping
    public ItemDto postItem(@Valid @RequestBody ItemDto itemDto,
                            @Size(min = 1) @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return service.addItem(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto patchItem(@RequestBody ItemDto itemDto,
                             @Size(min = 1) @PathVariable Long itemId,
                             @Size(min = 1) @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return service.updateItem(itemDto, itemId, userId);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@Size(min = 1) @PathVariable Long itemId) {
        service.deleteItem(itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@Size(min = 1) @PathVariable Long itemId) {
        return service.getItemById(itemId);
    }

    @GetMapping
    public List<ItemDto> getUserItems(@Size(min = 1) @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return service.getAllUserItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsByText(@NotBlank @RequestParam("text") String text) {
        return service.searchItemsByText(text);
    }

}