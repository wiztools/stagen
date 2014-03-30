package org.wiztools.stagen;

/**
 *
 * @author subwiz
 */
public class ExecutorException extends Exception {

    public ExecutorException(Throwable ex) {
        super(ex);
    }

    public ExecutorException(String msg) {
        super(msg);
    }

    public ExecutorException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
