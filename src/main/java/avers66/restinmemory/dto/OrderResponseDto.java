package avers66.restinmemory.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * OrderResponseDto
 *
 * @Author Tretyakov Alexandr
 */


@Data
public class OrderResponseDto {
    private Long id;
    private String product;
    private BigDecimal cost;
}
