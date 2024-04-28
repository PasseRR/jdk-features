function bars() {
    return {
        "/jdk11": {
            base: '/jdk11',
            items: [
                {text: 'Http客户端标准', link: '/http-client-standard'},
                {text: 'lambda参数的局部变量语法', link: '/lambda-local-variable-type-inference'},
                {
                    text: '新增API',
                    base: '/jdk11/api',
                    collapsed: false,
                    items: [
                        {text: 'String API新增', link: '/string'},
                        {text: '集合API新增', link: '/collection'},
                        {text: 'TimeUnit API新增', link: '/time-unit'},
                        {text: 'Predicate API新增', link: '/predicate'},
                        {text: 'Files API新增', link: '/files'},
                    ]
                }
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK11🔥',
        link: '/jdk11/index',
        activeMatch: '/jdk11/'
    };
}

export default {bars, nav}
