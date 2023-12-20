package avers66.restinmemory.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * OrderRequestDto
 *
 * @Author Tretyakov Alexandr
 */

@Data
public class OrderRequestDto {

    private Long id;
    private String product;
    private BigDecimal cost;
}
