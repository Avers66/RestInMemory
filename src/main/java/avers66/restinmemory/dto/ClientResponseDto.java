package avers66.restinmemory.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ResponseDto
 *
 * @Author Tretyakov Alexandr
 */

@Data
public class ClientResponseDto {

    private Long id;
    private String name;
    private List<OrderResponseDto> orders = new ArrayList<>();
}
