# netty-im

## 客户端与服务端通信协议编解码
1. 通信协议是为了服务端与客户端交互, 双方协商出来的满足一定规则的二进制数据格式
2.

## Netty 自带的拆包器
1. 固定长度的拆包器 FixedLengthFrameDecoder
如果你的应用层协议非常简单，每个数据包的长度都是固定的，比如 100，那么只需要把这个拆包器加到 pipeline 中，
Netty 会把一个个长度为 100 的数据包 (ByteBuf) 传递到下一个 channelHandler。
2. 行拆包器 LineBasedFrameDecoder
从字面意思来看，发送端发送数据包的时候，每个数据包之间以换行符作为分隔，
接收端通过 LineBasedFrameDecoder 将粘过的 ByteBuf 拆分成一个个完整的应用层数据包。
3. 分隔符拆包器 DelimiterBasedFrameDecoder
DelimiterBasedFrameDecoder 是行拆包器的通用版本，只不过我们可以自定义分隔符。
4. 基于长度域拆包器 LengthFieldBasedFrameDecoder
最后一种拆包器是最通用的一种拆包器，只要你的自定义协议中包含长度域字段，均可以使用这个拆包器来实现应用层拆包。