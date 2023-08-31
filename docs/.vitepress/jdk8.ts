function bars() {
    return {
        "/jdk8": {
            base: '/jdk8',
            items: [
                {text: '重复注解', link: '/repeatable-annotation'},
                {text: '接口默认方法和静态方法', link: '/interface-default-static-method'},
                {
                    text: '新日期、时间API',
                    base: '/jdk8/date-time',
                    link: '/index',
                    collapsed: false,
                    items: []
                },
                {text: 'Optional工具类', link: '/optional'},
                {text: 'Accumulator和Adder工具类', link: '/accumulator-adder'},
                {
                    text: '函数式接口',
                    base: 'jdk8/functional',
                    link: '/index',
                    collapsed: false,
                    items: []
                },
                {text: 'Lambda表达式', link: '/lambda'},
                {text: '方法引用', link: '/method-reference'},
                {text: 'Stream API', link: '/stream'},
                {text: 'CompletableFuture异步编程', link: '/completable-future'},
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK8🔥',
        link: '/jdk8/index',
        activeMatch: '/jdk8/'
    };
}

export default {bars, nav}
