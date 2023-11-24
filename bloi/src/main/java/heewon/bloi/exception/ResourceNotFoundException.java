package heewon.bloi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value= HttpStatus.NOT_FOUND) // 아래 exception이 발생하면 특정 http status를 반환함.
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String filedName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String filedName, long fieldValue) {
        super(String.format("%s not found with %s", resourceName, filedName, fieldValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
