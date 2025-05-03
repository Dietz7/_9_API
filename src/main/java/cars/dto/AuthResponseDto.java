package cars.dto;
import lombok.*;


@Getter
@Setter
@ToString
@Builder

public class AuthResponseDto {
    private String accessToken;
}
