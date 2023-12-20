package avers66.restinmemory.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ListResponseDto
 *
 * @Author Tretyakov Alexandr
 */

@Data
public class ListClientResponseDto {
    private List<ClientResponseDto> clients = new ArrayList<>();
}
