/**
 * 模块化文件
 * @author xiehai
 * @date 2021/11/21 19:03
 */
module jdk.features {
    // 所需要用到的模块名
    requires java.base;
    // 引入http客户端孵化器
    requires jdk.incubator.httpclient;
    requires java.desktop;
    // 模块传递 引用jdk.features自动引用java.logging模块
    requires transitive java.logging;
    // 编译需要 运行时选择
    requires static java.compiler;
    // 导出包
    exports feature2;
    // 导出包给某个或多个模块
    exports feature3 to java.base, java.compiler;
    // 编译时不可用 运行时可用
    opens feature2;
}