<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">  
    <title>a</title>  
    <style>  
        body{background-color:#000000}  
    </style>  
</head>  
<body>  
<div id="main" style="height:500px;width: 100%; margin-left: 0px;float: left;"></div>  
<!--    本地引入ECharts,'js/dist/'本地文件路径下 -->  
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>  
<script src="http://echarts.baidu.com/doc/example/timelineOption.js"></script>  
<script type="text/javascript">  
    //        路径配置,'./js/dist'本地文件路径  
    require.config({  
        paths: {  
            echarts: 'http://echarts.baidu.com/build/dist'  
        }  
    });  
    require(  
            [  
                'echarts',  
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表  
                'echarts/chart/bar'  
            ],  
            function (ec) {  
                var myChart = ec.init(document.getElementById('main'));  
                option = {  
                    timeline:{  
                        data:[  
                            '2002-01-01','2003-01-01','2004-01-01','2005-01-01','2006-01-01',  
                            '2007-01-01','2008-01-01','2009-01-01','2010-01-01','2011-01-01'  
                        ],  
                        label : {  
                            formatter : function(s) {  
                                return s.slice(0, 4);  
                            }  
                        },  
                        autoPlay : true,  
                        playInterval : 1000  
                    },  
                    options:[  
                        {  
                            title : {  
                                'text':'2002全国宏观经济指标',  
                                'subtext':'数据来自国家统计局'  
                            },  
                            tooltip : {'trigger':'axis'},  
                            legend : {  
                                x:'right',  
                                'data':['GDP','金融','房地产','第一产业','第二产业','第三产业'],  
                                'selected':{  
                                    'GDP':true,  
                                    '金融':false,  
                                    '房地产':true,  
                                    '第一产业':false,  
                                    '第二产业':false,  
                                    '第三产业':false  
                                }  
                            },  
                            toolbox : {  
                                'show':true,  
                                orient : 'vertical',  
                                x: 'right',  
                                y: 'center',  
                                'feature':{  
                                    'mark':{'show':true},  
                                    'dataView':{'show':true,'readOnly':false},  
                                    'magicType':{'show':true,'type':['line','bar','stack','tiled']},  
                                    'restore':{'show':true},  
                                    'saveAsImage':{'show':true}  
                                }  
                            },  
                            calculable : true,  
                            grid : {'y':80,'y2':100},  
                            xAxis : [{  
                                'type':'category',  
                                'axisLabel':{'interval':0},  
                                'data':[  
                                    '北京','\n天津','河北','\n山西','内蒙古','\n辽宁','吉林','\n黑龙江',  
                                    '上海','\n江苏','浙江','\n安徽','福建','\n江西','山东','\n河南',  
                                    '湖北','\n湖南','广东','\n广西','海南','\n重庆','四川','\n贵州',  
                                    '云南','\n西藏','陕西','\n甘肃','青海','\n宁夏','新疆'  
                                ]  
                            }],  
                            yAxis : [  
                                {  
                                    'type':'value',  
                                    'name':'GDP（亿元）',  
                                    'max':53500  
                                },  
                                {  
                                    'type':'value',  
                                    'name':'其他（亿元）'  
                                }  
                            ],  
                            series : [  
                                {  
                                    'name':'GDP',  
                                    'type':'bar',  
                                    'markLine':{  
                                        symbol : ['arrow','none'],  
                                        symbolSize : [4, 2],  
                                        itemStyle : {  
                                            normal: {  
                                                lineStyle: {color:'orange'},  
                                                barBorderColor:'orange',  
                                                label:{  
                                                    position:'left',  
                                                    formatter:function(params){  
                                                        return Math.round(params.value);  
                                                    },  
                                                    textStyle:{color:'orange'}  
                                                }  
                                            }  
                                        },  
                                        'data':[{'type':'average','name':'平均值'}]  
                                    },  
                                    'data': dataMap.dataGDP['2002']  
                                },  
                                {  
                                    'name':'金融','yAxisIndex':1,'type':'bar',  
                                    'data': dataMap.dataFinancial['2002']  
                                },  
                                {  
                                    'name':'房地产','yAxisIndex':1,'type':'bar',  
                                    'data': dataMap.dataEstate['2002']  
                                },  
                                {  
                                    'name':'第一产业','yAxisIndex':1,'type':'bar',  
                                    'data': dataMap.dataPI['2002']  
                                },  
                                {  
                                    'name':'第二产业','yAxisIndex':1,'type':'bar',  
                                    'data': dataMap.dataSI['2002']  
                                },  
                                {  
                                    'name':'第三产业','yAxisIndex':1,'type':'bar',  
                                    'data': dataMap.dataTI['2002']  
                                }  
                            ]  
                        },  
                        {  
                            title : {'text':'2003全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2003']},  
                                {'data': dataMap.dataFinancial['2003']},  
                                {'data': dataMap.dataEstate['2003']},  
                                {'data': dataMap.dataPI['2003']},  
                                {'data': dataMap.dataSI['2003']},  
                                {'data': dataMap.dataTI['2003']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2004全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2004']},  
                                {'data': dataMap.dataFinancial['2004']},  
                                {'data': dataMap.dataEstate['2004']},  
                                {'data': dataMap.dataPI['2004']},  
                                {'data': dataMap.dataSI['2004']},  
                                {'data': dataMap.dataTI['2004']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2005全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2005']},  
                                {'data': dataMap.dataFinancial['2005']},  
                                {'data': dataMap.dataEstate['2005']},  
                                {'data': dataMap.dataPI['2005']},  
                                {'data': dataMap.dataSI['2005']},  
                                {'data': dataMap.dataTI['2005']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2006全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2006']},  
                                {'data': dataMap.dataFinancial['2006']},  
                                {'data': dataMap.dataEstate['2006']},  
                                {'data': dataMap.dataPI['2006']},  
                                {'data': dataMap.dataSI['2006']},  
                                {'data': dataMap.dataTI['2006']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2007全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2007']},  
                                {'data': dataMap.dataFinancial['2007']},  
                                {'data': dataMap.dataEstate['2007']},  
                                {'data': dataMap.dataPI['2007']},  
                                {'data': dataMap.dataSI['2007']},  
                                {'data': dataMap.dataTI['2007']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2008全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2008']},  
                                {'data': dataMap.dataFinancial['2008']},  
                                {'data': dataMap.dataEstate['2008']},  
                                {'data': dataMap.dataPI['2008']},  
                                {'data': dataMap.dataSI['2008']},  
                                {'data': dataMap.dataTI['2008']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2009全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2009']},  
                                {'data': dataMap.dataFinancial['2009']},  
                                {'data': dataMap.dataEstate['2009']},  
                                {'data': dataMap.dataPI['2009']},  
                                {'data': dataMap.dataSI['2009']},  
                                {'data': dataMap.dataTI['2009']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2010全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2010']},  
                                {'data': dataMap.dataFinancial['2010']},  
                                {'data': dataMap.dataEstate['2010']},  
                                {'data': dataMap.dataPI['2010']},  
                                {'data': dataMap.dataSI['2010']},  
                                {'data': dataMap.dataTI['2010']}  
                            ]  
                        },  
                        {  
                            title : {'text':'2011全国宏观经济指标'},  
                            series : [  
                                {'data': dataMap.dataGDP['2011']},  
                                {'data': dataMap.dataFinancial['2011']},  
                                {'data': dataMap.dataEstate['2011']},  
                                {'data': dataMap.dataPI['2011']},  
                                {'data': dataMap.dataSI['2011']},  
                                {'data': dataMap.dataTI['2011']}  
                            ]  
                        }  
                    ]  
                };  
  
  
                myChart.setOption(option);  
            }  
    );  
</script>  
  
  
</body>  
</html>