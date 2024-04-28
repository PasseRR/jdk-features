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
                    items: [{
                        text: '框架',
                        base: '/jdk8/date-time/framework',
                        collapsed: true,
                        items: [
                            {text: 'temporal', link: '/temporal'},
                            {text: 'chrono', link: '/chrono'},
                            {text: 'zone', link: '/zone'},
                            {text: 'format', link: '/format'},
                        ]
                    }]
                },
                {text: 'Optional工具类', link: '/optional'},
                {text: 'Accumulator和Adder工具类', link: '/accumulator-adder'},
                {
                    text: '函数式接口',
                    base: '/jdk8/functional',
                    link: '/index',
                    collapsed: false,
                    items: [
                        {text: '@FunctionalInterface', link: '/functional-interface'},
                        {text: 'Consumer', link: '/consumer'},
                        {text: 'Function', link: '/function'},
                        {text: 'Supplier', link: '/supplier'},
                        {text: 'Predicate', link: '/predicate'},
                        {text: 'Lambda表达式', link: '/lambda'},
                        {text: '方法引用', link: '/method-reference'},
                    ]
                },
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
