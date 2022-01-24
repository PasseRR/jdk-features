package feature11;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * @author xiehai
 * @date 2022/01/20 09:49
 */
public class VarHandleSpec {
    PrivateVar privateVar = new PrivateVar();
    volatile int cnt;
    static final VarHandle VAR;
    static final VarHandle PRIVATE_VAR;

    static {
        try {
            // 可见变量
            VAR = MethodHandles.lookup().findVarHandle(VarHandleSpec.class, "cnt", int.class);
            // 私有变量
            PRIVATE_VAR =
                MethodHandles.privateLookupIn(PrivateVar.class, MethodHandles.lookup())
                    .findVarHandle(PrivateVar.class, "cnt", int.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public void init() {
        this.cnt = 0;
    }

    public int increment0() {
        this.cnt++;
        return this.cnt;
    }

    public int increment() {
        return (int) VAR.getAndAdd(this, 1);
    }

    public int incrementPrivate() {
        PRIVATE_VAR.getAndAdd(this.privateVar, 1);
        return this.privateVar.getCnt();
    }

    public static void main(String[] args) {
        VarHandleSpec spec = new VarHandleSpec();

        IntStream.range(0, 100).parallel().forEach(it -> ForkJoinPool.commonPool().execute(spec::increment0));
        System.out.println(spec.cnt);

        spec.init();

        IntStream.range(0, 100).parallel().forEach(it -> ForkJoinPool.commonPool().execute(spec::increment));
        IntStream.range(0, 100).parallel().forEach(it -> ForkJoinPool.commonPool().execute(spec::incrementPrivate));
        System.out.println(spec.cnt);
        System.out.println(spec.privateVar.getCnt());
    }
}

class PrivateVar {
    private int cnt;

    public int getCnt() {
        return cnt;
    }
}
