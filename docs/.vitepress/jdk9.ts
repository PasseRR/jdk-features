function bars() {
    return {
        "/jdk9": {
            base: '/jdk9',
            items: [
                {text: '模块化', link: '/modules'},
                {text: '集合工厂', link: '/collection-factory'},
                {text: '接口私有方法', link: '/interface-private-method'},
                {text: '进程API', link: '/process-api'},
                {text: 'Stream API改进', link: '/stream-api-improve'},
                {text: 'Optional API改进', link: '/optional-api-improve'},
                {text: 'try-with-resources改进', link: '/try-with-resources-improve'},
                {text: 'Deprecated注解', link: '/deprecated-annotation'},
                {text: '内部类的钻石操作符', link: '/inner-class-diamond-operator'},
                {text: 'CompletableFuture API改进', link: '/completable-future-api-improve'},
                {text: 'VarHandle', link: '/var-handle'},
                {text: 'Reactive Stream响应式流API', link: '/reactive-stream-api'},
                {text: '🐣Http/2.0客户端', link: '/http2-client'},
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK9',
        link: '/jdk9/index',
        activeMatch: '/jdk9/'
    };
}

export default {bars, nav}
