package pers.jim.shellrun;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArgumentModel {
    private String os;
    private String command;
}
