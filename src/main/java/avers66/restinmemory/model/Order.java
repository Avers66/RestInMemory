package avers66.restinmemory.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Order
 *
 * @Author Tretyakov Alexandr
 */


@Data
public class Order {

    private Long id;
    private String product ;
    private BigDecimal cost;
    private Instant createAt;
    private Instant updateAt;
    private Long clientId;



}
