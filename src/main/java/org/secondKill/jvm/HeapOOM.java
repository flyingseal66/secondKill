package org.secondKill.jvm;

import org.secondKill.entity.Seckill;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeapOOM {

    private List<Seckill> list = new ArrayList<>();

    @GetMapping("/heap")
    public void heap(String[] args) {
        HeapOOM oom = new HeapOOM();
        long l = 100l;

        while (true) {
            oom.list.add(new Seckill(l++, "2222"));
        }
    }
}
