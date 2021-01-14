package qyh.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 实现交替打印A B C10次
 */
public class AlternatePrint {
    public static void main(String[] args) {
        Future<Integer> f = new FutureTask<>(new Printer(null, null));
    }

    public static class Printer implements Callable<Integer> {
        public Printer(Object prev, Object self) {

        }

        @Override
        public Integer call() {
            return 0;
        }
    }
}
