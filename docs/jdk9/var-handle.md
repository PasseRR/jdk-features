# VarHandle

## 概念
`VarHandle`是新的原子访问属性规范，JDK8以前都是通过`Unsafe`实现原子属性访问，从JDK9开始，
会尽可能使用`VarHandle`代替`Unsafe`，除了`atomic`包下一些依赖问题没解决，很多API都使用
`VarHandle`代替了。

`VarHandle`提供了一系列标准的内存屏障操作，用于更加细粒度的控制内存排序。在安全性、可用性、性能上都要优于现有的API。
`VarHandle`可以与任何字段、数组元素或静态变量关联，支持在不同访问模型下对这些类型变量的访问，包括简单的read/write访问，
volatile类型的 read/write访问，和CAS(compare-and-swap)等。

## VarHandle创建方法

通过`MethodHandles`的lookup方法创建。

- findVarHandle  
传入字段所在的class，字段名，字段类型class
- findStaticVarHandle  
传入静态字段所在class，字段名，字段类型class
- unreflectVarHandle  
传入反射Field类型

## 方法说明

### 访问模式
- plain  
普通访问，无方法后缀，不保证内存可见性及执行顺序
- opaque  
保证执行顺序，不保证内存可见性
- acquire/release  
保证执行顺序，setRelease确保前面的load和store不会被重排序到后面，但不确保后面的load和store重排序到前面；getAcquire确保后面的load和store不会被重排序到前面，但不确保前面的load和store被重排序。
- volatile
保证执行顺序，且保证变量不会被重排

### 访问类别
1. get
   - get
   - getVolatile
   - getAcquire
   - getOpaque
2. set
   - set
   - setVolatile
   - setRelease
   - setOpaque
3. CAS(compare-and-swap)
   - compareAndSet
   - weakCompareAndSetPlain
   - weakCompareAndSet
   - weakCompareAndSetAcquire
   - weakCompareAndSetRelease
4. CAE(compare-and-exchange)
   - compareAndExchange
   - compareAndExchangeAcquire
   - compareAndExchangeRelease
5. GAU(get-and-update)
   - getAndAdd
   - getAndAddAcquire
   - getAndAddRelease
   - getAndBitwiseOr
   - getAndBitwiseOrRelease
   - getAndBitwiseOrAcquire
   - getAndBitwiseAnd
   - getAndBitwiseAndRelease
   - getAndBitwiseAndAcquire
   - getAndBitwiseXor
   - getAndBitwiseXorRelease
   - getAndBitwiseXorAcquire

### 内存屏障

内存屏障相关方法均为`static`静态方法。

| 方法名             | 方法描述                         |
|:----------------|:-----------------------------|
| fullFence       | 保证方法调用之前的所有读写操作不会被方法后的读写操作重排 |
| acquireFence    | 保证方法调用之前的所有读操作不会被方法后的读写操作重排  |
| releaseFence    | 保证方法调用之前的所有读写操作不会被方法后的写操作重排  |
| loadLoadFence   | 保证方法调用之前的所有读操作不会被方法后的读操作重排   |
| storeStoreFence | 保证方法调用之前的所有写操不会被方法后的写操作重排    |

## 示例

```java
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
```