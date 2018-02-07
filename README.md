# VipCard

本项目内涵listview，cardview，二维码条形码的扫描和使用，sqldatabase的基本用法，如果您对这方面不算很熟悉，可以下载来看看。

有问题可以随时Issue，也欢迎联系本人mafanwei@outlook.com，喜欢给个star哦！

里面的zxing用的是https://github.com/xuyisheng/ZXingLib 这个项目基于zxing 3.1，算是比较新的了。

这个并不能生成条形码，我在libzxing/zxing/encoding/EncodingUtils填加了生成条形码的方法，下载本项目，直接解压libzxing即可使用。

关于踩坑，ImageView.setImageBitmap()不能自动填满整个控件，在生成bitmap的时候尺寸过大会导致oom，这里用生成bitmap后拉伸的方法来填满控件

即用ImageView.setScaleType(ImageView.ScaleType.FIT_XY);

注意，此方法应该在setImageBitmap()后调用。

(http://github.com/mfanwei/VipCard//raw/master/Screenshot_20180207-101446.png)

(https://github.com/linwh8/ModernWebPrograming/raw/master/My_image/Calculator.png)



