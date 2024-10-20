package com.order.rush_order.common.entity;


import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Timestamp {

    @CreatedDate
    private LocalDateTime createAt;
}
