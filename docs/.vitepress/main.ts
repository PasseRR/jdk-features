import jdk8 from "./jdk8";
import jdk9 from "./jdk9";
import jdk10 from "./jdk10";
import jdk11 from "./jdk11";
import jdk12 from "./jdk12";
import jdk13 from "./jdk13";

const site = {
    main: 'https://www.xiehai.zone',
    logo: '/logo.svg',
    // 标题
    title: 'JDK新特性',
    // 描述
    description: '各个JDK版本新特性',
    // github仓库
    repository: 'jdk-features',
    // 主分支
    branch: 'main',
    // 基础路径
    base: '/jdk-features',
    // google 分析
    google: 'G-1L1DPX3PFD',
    // 百度统计
    baidu: '95dc7dd4cb7bead6ab60cf81fab91330',
    // 排除文件
    excludes: []
}

function sidebars() {
    return {
        ...jdk8.bars(),
        ...jdk9.bars(),
        ...jdk10.bars(),
        ...jdk11.bars(),
        ...jdk12.bars(),
        ...jdk13.bars(),
    };
}

function navs() {
    return [jdk13.nav(), jdk12.nav(), jdk11.nav(), jdk10.nav(), jdk9.nav(), jdk8.nav()];
}

export {site, sidebars, navs};
