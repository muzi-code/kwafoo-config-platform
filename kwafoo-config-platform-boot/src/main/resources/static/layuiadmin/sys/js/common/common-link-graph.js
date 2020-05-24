var tooltip = {                             //提示框组件
    trigger: 'item',                            //触发类型,'item'数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。 'axis'坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
    formatter: function (params) {
        if (params.dataType === "edge") {
            return params.name;
        }else{
            return params.data.content.tips;
        }
    }
};

function generalLinkGraph(linkGraphChart,nodes,links,legend,categories) {
    linkGraphChart.showLoading();
    var option = {
        title: {
            show : false
        },                             // 标题设置
        tooltip: tooltip,              // 节点悬浮提示
        legend: legend,                // 分组筛选提示
        animationDurationUpdate: 2000, // 动画的时长。
        animationEasingUpdate: 'quinticInOut', // 动画的加载效果
        series: [
            {
                type: "graph",               //关系图
                top: '15%',                      //组件离容器上侧的距离，百分比字符串或整型数字
                bottom: "15%",              //组件离容器下侧的距离,百分比字符串或整型数字
                legendHoverLink: true,       //是否启用图例 hover 时的联动高亮。
                hoverAnimation: true,        //是否开启鼠标 hover 节点的提示动画效果。
                layout: 'force',              //'none' 不采用任何布局，使用节点中提供的 x， y 作为节点的位置.'circular' 采用环形布局，'force' 采用力引导布局。
                force: { //力引导图基本配置
                    //initLayout: , //力引导的初始化布局，默认使用xy轴的标点
                    //repulsion: 10, //节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                    gravity : 0.22, //节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                    edgeLength: 10, //边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 值越小则长度越长
                    layoutAnimation : true //因为力引导布局会在多次迭代后才会稳定，这个参数决定是否显示布局的迭代动画，在浏览器端节点数据较多（>100）的时候不建议关闭，布局过程会造成浏览器假死。
                },
                roam: true,                 //是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move'。设置成 true 为都开启
                nodeScaleRatio: 0.6,         //鼠标漫游缩放时节点的相应缩放比例，当设为0时节点不随着鼠标的缩放而缩放
                draggable: true,            //节点是否可拖拽，只在使用力引导布局的时候有用。
                focusNodeAdjacency: true,   //是否在鼠标移到节点上的时候突出显示节点以及节点的边和邻接节点。放大
                symbol: "circle",               //图形 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
                symbolSize: 50,              //标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
                edgeSymbol: ['circle', 'arrow'],   //边两端的标记类型，可以是一个数组分别指定两端，也可以是单个统一指定。默认不显示标记，常见的可以设置为箭头
                edgeSymbolSize: [2, 4],       //边两端的标记大小，可以是一个数组分别指定两端，也可以是单个统一指定。
                cursor: "pointer",           //鼠标悬浮时在图形元素上时鼠标的样式是什么。同 CSS 的 cursor。
                label: {                      //图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等，
                    show: true,
                    formatter: function (params) {
                        return params.data.nickname;    //节点提示节点信息,后端协定的nickname展示文案
                    }
                },
                edgeLabel: {
                },
                emphasis: {
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 1,
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.3)'
                },
                lineStyle: {
                    normal: {
                        opacity: 0.9,// 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。默认0.5
                        // type : 'dotted', //线的类型 'solid'（实线）'dashed'（虚线）'dotted'（点线）
                        width: 2,
                        // color: 'target',//决定边的颜色是与起点相同还是与终点相同
                        curveness: 0// //线条的曲线程度，从0到1
                    }
                },
                categories: categories,
                data: nodes,
                links: links
            }
        ]
    };
    linkGraphChart.clear();
    linkGraphChart.setOption(option);
    linkGraphChart.hideLoading();
    return linkGraphChart;
};

function singleSkuKnowledgeGraph(linkGraphChart,nodes,links,legend,categories) {
    linkGraphChart.showLoading();
    var option = {
        title: {
            show : false
        },                             // 标题设置
        tooltip: tooltip,              // 节点悬浮提示
        legend: legend,                // 分组筛选提示
        animationDurationUpdate: 2000, // 动画的时长。
        animationEasingUpdate: 'quinticInOut', // 动画的加载效果
        series: [
            {
                type: "graph",               //关系图
                top: '15%',                      //组件离容器上侧的距离，百分比字符串或整型数字
                bottom: "15%",              //组件离容器下侧的距离,百分比字符串或整型数字
                legendHoverLink: true,       //是否启用图例 hover 时的联动高亮。
                hoverAnimation: true,        //是否开启鼠标 hover 节点的提示动画效果。
                layout: 'force',              //'none' 不采用任何布局，使用节点中提供的 x， y 作为节点的位置.'circular' 采用环形布局，'force' 采用力引导布局。
                force: { //力引导图基本配置
                    //initLayout: , //力引导的初始化布局，默认使用xy轴的标点
                    repulsion: 50, //节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                    //gravity : 0.27, //节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                    edgeLength: 20, //边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 值越小则长度越长
                    layoutAnimation : true //因为力引导布局会在多次迭代后才会稳定，这个参数决定是否显示布局的迭代动画，在浏览器端节点数据较多（>100）的时候不建议关闭，布局过程会造成浏览器假死。
                },
                roam: true,                 //是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move'。设置成 true 为都开启
                nodeScaleRatio: 0.6,         //鼠标漫游缩放时节点的相应缩放比例，当设为0时节点不随着鼠标的缩放而缩放
                draggable: true,            //节点是否可拖拽，只在使用力引导布局的时候有用。
                focusNodeAdjacency: true,   //是否在鼠标移到节点上的时候突出显示节点以及节点的边和邻接节点。放大
                symbol: "circle",               //图形 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
                symbolSize: 50,              //标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
                edgeSymbol: ['circle', 'arrow'],   //边两端的标记类型，可以是一个数组分别指定两端，也可以是单个统一指定。默认不显示标记，常见的可以设置为箭头
                edgeSymbolSize: [2, 4],       //边两端的标记大小，可以是一个数组分别指定两端，也可以是单个统一指定。
                cursor: "pointer",           //鼠标悬浮时在图形元素上时鼠标的样式是什么。同 CSS 的 cursor。
                label: {                      //图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等，
                    show: true,
                    formatter: function (params) {
                        return params.data.nickname;    //节点提示节点信息,后端协定的nickname展示文案
                    }
                },
                edgeLabel: {
                },
                emphasis: {
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 1,
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.3)'
                },
                lineStyle: {
                    normal: {
                        opacity: 0.9,// 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。默认0.5
                        // type : 'dotted', //线的类型 'solid'（实线）'dashed'（虚线）'dotted'（点线）
                        width: 2,
                        // color: 'target',//决定边的颜色是与起点相同还是与终点相同
                        curveness: 0// //线条的曲线程度，从0到1
                    }
                },
                categories: categories,
                data: nodes,
                links: links
            }
        ]
    };
    linkGraphChart.clear();
    linkGraphChart.setOption(option);
    linkGraphChart.hideLoading();
    return linkGraphChart;
};