package org.secondKill.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {

    protected final Lock lock = new ReentrantLock();

    //condition : notFull (count < items.length)
    private final Condition notFull = lock.newCondition();
    //condition : notEmpty(count > 0)



}
