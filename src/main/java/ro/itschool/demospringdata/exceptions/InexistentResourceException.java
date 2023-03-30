package ro.itschool.demospringdata.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InexistentResourceException extends Exception{

    private String message;
    private Integer id;
}
