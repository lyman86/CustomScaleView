# CustomScaleView

第一步 在项目里的build.gradle里加上 
<br>
maven { url 'https://jitpack.io' }
<br>
<br>
第二部 在app的build.gradle里加上 
<br>
compile 'com.github.lyman86:CustomScaleView:v1.0.0'
<br>
# 改刻度尺有如下属性
<br>
1.整个刻度尺的高度，也就是这个指针的高度：lyman:scaleViewHeight="36dp"
<br>
2.整个刻度尺的长度：lyman:scaleViewLenth="2000"
<br>
3.刻度尺的刻度数目，如刻度尺的长度为2000，刻度数目为20，则每个刻度之间的距离为100：
<br>
4.刻度尺长直线的高度：lyman:scaleViewLineHeight="2dp"
<br>
5.刻度尺每个刻度的高度：lyman:scaleViewScaleHeight="5dp"
<br>
6.刻度尺每个刻度的宽度：lyman:scaleViewScaleWidth="2dp"
<br>
7.刻度尺指针的宽度：lyman:scaleViewPointerWidth="4dp"
<br>
8.刻度尺初始化所停留的位置，默认为0：lyman:scaleViewInitPos="100"
<br>
9.刻度尺长直线的颜色：lyman:scaleViewLineColor="@color/colorPrimary"
<br>
10.刻度尺只针的颜色：lyman:scaleViewPointerColor="@color/scroll_scaleView_green"
<br>
11.刻度尺每个刻度的颜色：lyman:scaleViewScaleColor="@color/colorAccent"
<br>

![image](https://github.com/lyman86/CustomScaleView/blob/master/app/screenshots/device-2018-04-07-233012.png)

![image](https://github.com/lyman86/CustomScaleView/blob/master/app/screenshots/device-2018-04-07-233629.png)
