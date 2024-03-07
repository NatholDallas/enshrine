package nathol.utils.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public final class ResponseEntity extends org.springframework.http.ResponseEntity<Object> {

    public ResponseEntity(Object data, HttpStatusCode httpStatusCode) {
        super(data, httpStatusCode);
    }

    public ResponseEntity(HttpStatusCode httpStatusCode) {
        super(httpStatusCode);
    }

    public static ResponseEntity success() {
        return new ResponseEntity(HttpStatus.OK);
    }

    public static ResponseEntity success(Object data) {
        return new ResponseEntity(data, HttpStatus.OK);
    }

    public static ResponseEntity error() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity error(Exception e) {
        return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

}
