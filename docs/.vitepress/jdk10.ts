function bars() {
    return {
        "/jdk10": {
            base: '/jdk10',
            items: [
                {text: '局部变量类型推断', link: '/local-variable-type-inference'},
                {
                    text: '新增API',
                    base: '/jdk10/api',
                    collapsed: false,
                    items: [
                        {text: 'Optional API新增', link: '/optional'},
                        {text: '集合API新增', link: '/collection'},
                        {text: 'Collectors API新增', link: '/collectors'},
                    ]
                }
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK10',
        link: '/jdk10/index',
        activeMatch: '/jdk10/'
    };
}

export default {bars, nav}
