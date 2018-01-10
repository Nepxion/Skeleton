package com.nepxion.skeleton.engine.exception;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2020</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

public class SkeletonException extends RuntimeException {
    private static final long serialVersionUID = 8031213882044757635L;

    public SkeletonException() {
        super();
    }

    public SkeletonException(String message) {
        super(message);
    }

    public SkeletonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkeletonException(Throwable cause) {
        super(cause);
    }
}