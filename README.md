# ScaleAnim
fromX, toX, fromY, toY这4个参数很好理解，我们重点看一下pivotX，pivotY是怎么计算的
- 从本身的位置缩放到另一个位置
这种情况下，我们关心的是缩放后的目标位置，这里有几个值需要先了解一些，目标view的右边（targetRight），初始view左边的距离(sourceLeft)，pivotX，初始view的宽(sourceWidth)，放大的值(toX)，他们的关系如下
targetRight - sourceLeft = pivotX - (pivotX - sourceWidth) * toX，那么pivotX的值是pivotX = (targetRight - sourceLeft - sourceWidth * toX) / (1 - toX)

pivotY的值类似，就不在描述了。

- 从另一个位置缩放到本身的位置
这种情况我们关心的是开始的位置，它们的关系是sourceLeft - targetLeft = pivotX * (1 - scaleX)，那么pivotX = (sourceLeft - targetLeft) / (1 - fromX)
