package com.order.rush_order.order.dto;

import com.order.rush_order.order.dto.response.WishlistItemDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WishlistResponseDto {
    private List<WishlistItemDto> wishlistItems;
}
