package org.secondKill.jvmTest;

import org.secondKill.entity.Seckill;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HeapTest {

    static class OOMObject{
        private final static String oom = "OOM";
    }

    public static void main(String[] args) {

        final Seckill seckill = new Seckill(100l, "111");
        seckill.setName("222");

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
            AtomicInteger a = new AtomicInteger();
            a.incrementAndGet();
        }



    }
}
