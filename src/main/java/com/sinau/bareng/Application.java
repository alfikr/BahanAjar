package com.sinau.bareng;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application {
    public static void main(String[] args) {
        long start =System.currentTimeMillis();
        Flux.fromStream(IntStream.range(1,1000)
                        .mapToObj(i-> "Nilai "+i).parallel())
//                .log()
                .map(s->{
                    Map m  = new HashMap();
                    m.put("result",s);
                    return m;
                })
                .doOnNext(m->new Thread(()->{
                    m.entrySet().stream()
                            .forEach((k)->{
                                System.out.println(k);
                            });
                }).start())

                .doOnNext(m->new Thread(()->{
                    m.entrySet().stream()
                            .forEach((k)->{
                                Mono.error(new IllegalArgumentException("test aja"));
                            });
                }).start())
                .doOnComplete(() -> {
                    long end = System.currentTimeMillis();
                    System.out.println("result complete "+ (end-start) + " ms");
                })
                .parallel(10)
                .subscribe();
        long end = System.currentTimeMillis();
        System.out.println("running time "+(end-start)+"ms");
    }
}
