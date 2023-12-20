package avers66.restinmemory.exception;

/**
 * EntityNotFoundException
 *
 * @Author Tretyakov Alexandr
 */

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
