---
layout: page
title: 责任链模式
permalink: chain_of_responsibility.html
---

> 在责任链模式里，很多对象由每一个对象对其下家的引用而连接起来形成一条链。请求在这个链上传递，直到链上的某一个对象决定处理此请求。发出这个请求的客户端并不知道链上的哪一个对象最终处理这个请求，这使得系统可以在不影响客户端的情况下动态地重新组织和分配责任。  

### JDK中的使用
- java.util.logging.Logger#log(java.util.logging.LogRecord)
- javax.servlet.Filter#doFilter()
- dubbo、motan中的filter链

### 代码实现
在公司申请经费在一定金额限制下分别可以由leader、manager、boss同意  
```java
// 申请请求
@Data
public class HandleRequest {
    /**
     * 活动主体
     */
    private String activityTheme;
    /**
     * 申请活动资金
     */
    private BigDecimal activityCapital;
}
// 申请结果
@Data
@Builder
public class HandleResult {
    private Boolean isOk;
    private String from;
    private String result;

    public static HandleResult ok(String from, String result){
        return HandleResult.builder()
            .isOk(Boolean.TRUE)
            .from(from)
            .result(result)
            .build();
    }

    public static HandleResult reject(String from, String result){
        return HandleResult.builder()
            .isOk(Boolean.FALSE)
            .from(from)
            .result(result)
            .build();
    }
}

public interface Handler {
    HandleResult handle(HandleRequest request);
}
public abstract class AbstractHandler implements Handler {
    @Getter
    private Handler superior;

    public AbstractHandler() {

    }

    public AbstractHandler(Handler superior) {
        this.superior = superior;
    }

    /**
     * 职位名称
     * @return 职位名称
     */
    protected abstract String getPosition();

    /**
     * 当前是否能审批
     * @param request 请求
     * @return true/能 false/不能
     */
    protected abstract boolean canApprove(HandleRequest request);

    @Override
    public HandleResult handle(HandleRequest request) {
        if (this.canApprove(request)) {
            return HandleResult.ok(this.getPosition(), request.getActivityTheme() + " ok");
        }

        return this.superior != null
            ? this.superior.handle(request)
            : HandleResult.reject(this.getPosition(), request.getActivityTheme() + " reject");
    }
}
public class Leader extends AbstractHandler {
    public Leader(){
        super();
    }

    public Leader(Handler handler){
        super(handler);
    }
    @Override
    protected String getPosition() {
        return "leader";
    }
    @Override
    protected boolean canApprove(HandleRequest request) {
        // leader只能审批100元及以下的金额
        return request.getActivityCapital().compareTo(new BigDecimal("100")) <= 0;
    }
}
public class Manager extends AbstractHandler {
    public Manager(){
        super();
    }

    public Manager(Handler handler){
        super(handler);
    }

    @Override
    protected String getPosition() {
        return "manager";
    }

    @Override
    protected boolean canApprove(HandleRequest request) {
        // manager只能审批500元及以下的金额
        return request.getActivityCapital().compareTo(new BigDecimal("500")) <= 0;
    }
}
public class Boss extends AbstractHandler {
    public Boss(){
        super();
    }

    public Boss(Handler handler){
        super(handler);
    }

    @Override
    protected String getPosition() {
        return "boss";
    }

    @Override
    protected boolean canApprove(HandleRequest request) {
        // boss只同意1000元及以下金额
        return request.getActivityCapital().compareTo(new BigDecimal("1000")) <= 0;
    }
}
```

### 单元测试
```groovy
class AbstractHandlerSpec extends Specification {
    def leader() {
        given:
        def leader = new Leader()
        def handleResult = leader.handle(new HandleRequest(activityTheme: activityTheme, activityCapital: activityCapital))
        expect:
        handleResult.getIsOk() == isOk
        handleResult.getFrom() == fromPosition
        handleResult.getResult() == resultMessage

        where:
        activityCapital       | activityTheme || fromPosition || resultMessage || isOk
        new BigDecimal("100") | "团建"          || "leader"     || "团建 ok"       || Boolean.TRUE
        new BigDecimal("101") | "ktv"         || "leader"     || "ktv reject"  || Boolean.FALSE
    }

    def manager() {
        given:
        def manager = new Manager()
        def leader = new Leader(manager)
        def handleResult = leader.handle(new HandleRequest(activityTheme: activityTheme, activityCapital: activityCapital))
        expect:
        handleResult.getIsOk() == isOk
        handleResult.getFrom() == fromPosition
        handleResult.getResult() == resultMessage

        where:
        activityCapital       | activityTheme || fromPosition || resultMessage || isOk
        new BigDecimal("100") | "团建"          || "leader"     || "团建 ok"       || Boolean.TRUE
        new BigDecimal("101") | "ktv"         || "manager"    || "ktv ok"      || Boolean.TRUE
        new BigDecimal("499") | "乒乓球"         || "manager"    || "乒乓球 ok"      || Boolean.TRUE
        new BigDecimal("501") | "室内篮球"        || "manager"    || "室内篮球 reject" || Boolean.FALSE
    }

    def boss() {
        given:
        def boss = new Boss()
        def manager = new Manager(boss)
        def leader = new Leader(manager)
        def handleResult = leader.handle(new HandleRequest(activityTheme: activityTheme, activityCapital: activityCapital))
        expect:
        handleResult.getIsOk() == isOk
        handleResult.getFrom() == fromPosition
        handleResult.getResult() == resultMessage

        where:
        activityCapital        | activityTheme || fromPosition || resultMessage || isOk
        new BigDecimal("100")  | "团建"          || "leader"     || "团建 ok"       || Boolean.TRUE
        new BigDecimal("101")  | "ktv"         || "manager"    || "ktv ok"      || Boolean.TRUE
        new BigDecimal("499")  | "乒乓球"         || "manager"    || "乒乓球 ok"      || Boolean.TRUE
        new BigDecimal("501")  | "室内篮球"        || "boss"       || "室内篮球 ok"     || Boolean.TRUE
        new BigDecimal("1000") | "旅游"          || "boss"       || "旅游 ok"       || Boolean.TRUE
        new BigDecimal("1001") | "显卡"          || "boss"       || "显卡 reject"   || Boolean.FALSE
    }
}
```