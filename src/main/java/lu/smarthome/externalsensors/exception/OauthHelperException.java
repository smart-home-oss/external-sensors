package lu.smarthome.externalsensors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OauthHelperException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Internal error occurred, please check the logs or contact the Operations.";
    }
}
