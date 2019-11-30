package pers.jim.shellrun;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnModel {
    private boolean succeed;
    private String messages;
}
