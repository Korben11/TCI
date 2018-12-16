package service.crawlers;

/**
 * own exception class. Used when there is not next page to crawl
 */
public class NextPageDoesNotExistsException extends Exception{

    public NextPageDoesNotExistsException() {
    }

    public NextPageDoesNotExistsException(String message) {
        super(message);
    }

    public NextPageDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NextPageDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public NextPageDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
