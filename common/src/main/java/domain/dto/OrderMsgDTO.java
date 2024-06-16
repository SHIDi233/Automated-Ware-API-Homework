package domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMsgDTO {

    private String number;
    private int type;
    private int wareID;
    private int cargoID;
    private int num;

}
