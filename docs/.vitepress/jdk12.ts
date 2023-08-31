function bars() {
    return {
        "/jdk12": {
            base: '/jdk12',
            items: [
                {text: 'switch表达式(预览)', link: '/switch-preview'},
                {
                    text: '新增API',
                    base: 'jdk12/api',
                    items: [
                        {text: 'Files API新增', link: '/files'},
                        {text: '简化数字格式API', link: '/number-compact'},
                        {text: 'Collectors API新增', link: '/collectors'},
                    ]
                }
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK12',
        link: '/jdk12/index',
        activeMatch: '/jdk12/'
    };
}

export default {bars, nav}
