function bars() {
    return {
        "/jdk13": {
            base: '/jdk13',
            items: [
                {text: 'switch表达式(二次预览)', link: '/switch-preview'},
                {text: '文本块(预览)', link: '/text-block-preview'},
            ]
        }
    };
}

function nav() {
    return {
        text: 'JDK13',
        link: '/jdk13/index',
        activeMatch: '/jdk13/'
    };
}

export default {bars, nav}
