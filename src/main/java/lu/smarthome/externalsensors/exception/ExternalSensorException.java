package lu.smarthome.externalsensors.exception;

import org.springframework.http.HttpStatus;

public class ExternalSensorException extends RuntimeException {
    private HttpStatus statusCode;

    public ExternalSensorException(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return String.format(
                "Error loading sensor information, status code: %d, reason: %s",
                statusCode.value(),
                statusCode.getReasonPhrase()
        );
    }
}
