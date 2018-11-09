Android的attrs.xml的用法

http://www.cnblogs.com/yishujun/p/5555333.html

注意用自定义的属性的时候，布局文件一定要写：

![](file:///C:\Users\11699\AppData\Local\Temp\ksohtml\wpsBDC8.tmp.jpg)

用的时候：

![](file:///C:\Users\11699\AppData\Local\Temp\ksohtml\wpsBDC9.tmp.jpg)

attrs.xml的用途是扩展自定义view的属性的，里面放的都是属性

例子：

<?_**xml version=****"1.0"** **encoding=****"utf-8"**_?><resources>
<declare-styleable name=****“CircleImageView”>
<attr name=****“border_width” format=****“dimension” />
<attr name=****“border_color” format=****“color” />
</declare-styleable>

<declare-styleable name=****“CustomLineChart”>
<attr name=****“lineColor” format=****“color”/>
<attr name=****“shadowColor” format=****“color”/>
<attr name=****“pointNum” format=****“integer”/>
</declare-styleable>
</resources>

![](file:///C:\Users\11699\AppData\Local\Temp\ksohtml\wpsBDCA.tmp.jpg)

Android attr 的作用

<![if !supportLists]>（1）<![endif]>attr 作用就是约束属性数据类型，xml资源文件中定义各种attr，指定attr的数据类型。

Android attr 的使用方式

（1） 在自定义View的构造函数中解析这些从XML中定义的属性值，将其存放到View对应的成员变量中

<![if !supportLists]>（2）<![endif]>在layout文件中为自定义View的XML属性赋值

typedarray__是专门放控件的属性的地方

在自定义view的时候，我们需要用typedarray来存放控件的属性，并且也要通过他拿到控件值，并且修改控件的样式属性值

首先通过__obtainStyledAttributes__方法获取__typedarray

TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomLineChart);

// R.styleable.CustomLineChart****这个是

![](file:///C:\Users\11699\AppData\Local\Temp\ksohtml\wpsBDCB.tmp.jpg)

然后获取这些控件属性值得方法：

private int pointNum; // 折点个数private int lineColor; // 折线颜色private int shadowColor; // 阴影颜色

//Color.BLACK是当没设置属性的时候的默认值

lineColor=typedArray.getColor(R.styleable.CustomLineChart_lineColor, Color.BLACK);

shadowColor = typedArray.getColor(R.styleable.CustomLineChart_shadowColor, Color.BLACK);
pointNum = typedArray.getInteger(R.styleable.CustomLineChart_pointNum, 0);

利用Path还可以绘制各种各样的折线图

（path有路劲的意思，所以他画的是一个路径，将不同的路径连在一起就可以画不同的形状了）

path=new Path();

path.moveTo(0, 0); //设置绘制的起点在左上角

for (int i = 1; i <=15; i++) {

path.lineTo(i*20, (float)Math.random()*60);//这个是连接每个点

}
