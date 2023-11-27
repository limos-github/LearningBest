package com.limo.lb.learn.juc;

import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: Limo
 * @time: 2023-09-14 17:43
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
//        System.out.println("1");
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "\n11")
//                .thenCombine(CompletableFuture.supplyAsync(() -> "\n12")
//                        , (one, two) -> one + two);
//        System.out.println("1");
//        System.out.println(cf.join());
//
//        System.out.println("2");
//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "\n21")
//                .thenApply(one -> "\n22");
//        System.out.println("2");
//        System.out.println(cf1.join());
//
//        System.out.println("3");
//        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "\n31")
//                .thenApplyAsync(one -> "\n32");
//        System.out.println("3");
//        System.out.println(cf3.join());
//
//        System.out.println("4");
//        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(() -> "\n41")
//                .thenApplyAsync(one -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return "\n42";
//                })
//                .applyToEither(CompletableFuture.supplyAsync(() -> "\n43"), one -> one);
//        System.out.println("4");
//        System.out.println(cf4.join());

//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "打车").thenApplyAsync(one -> {
//            List<Integer> collect = IntStream.rangeClosed(1, 10).mapToObj(l -> l).collect(Collectors.toList());
//            return "55先到了";
//        }).applyToEither(CompletableFuture.supplyAsync(() -> "66车先到了"), one -> {
//            System.out.println(one + "车坏了");
//            float a = 3 / 0;
//            return one + "车坏了";
//        })
//                .thenApply(one -> one + "坐车回去")
//                .exceptionally(ex -> "走路回去");
//        System.out.println(stringCompletableFuture.join());
        System.out.println(CompletableFuture.runAsync(() -> {
            float a = 3 / 0;
        })
                .thenCombine(CompletableFuture.supplyAsync(() -> "地铁").exceptionally(ex -> "地铁坏了"), (one, two) -> one + two).join());

//        CompletableFuture<Integer> bus55 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(5000); // 模拟 55 路公交车的到来，等待 5 秒
//                return 55;
//            } catch (InterruptedException e) {
//                return -1; // 表示 55 路公交车坏了
//            }
//        });
//
//        CompletableFuture<Integer> bus66 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000); // 模拟 66 路公交车的到来，等待 3 秒
//                return 66;
//            } catch (InterruptedException e) {
//                return -1; // 表示 66 路公交车坏了
//            }
//        });
//
//        CompletableFuture<Integer> firstBus = bus55
//                .applyToEither(bus66, result -> {
//                    if (result != -1) {
//                        System.out.println(result + " 路公交车到了，小明上车。");
//                    }
//                    return result;
//                })
//                .exceptionally(ex -> {
//                    System.err.println("公交车坏了，小明走路回去了。");
//                    return -1;
//                });
//
//        firstBus.join(); // 等待第一个公交车到来并上车或者坏了
    }
}