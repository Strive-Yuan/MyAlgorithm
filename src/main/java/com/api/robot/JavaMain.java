package com.api.robot;

import kotlin.coroutines.Continuation;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

public class JavaMain {
    public static void main(String[] args) {
        System.out.println(111);
        Bot bot = BotFactory.INSTANCE.newBot(1746703001, "yjhui13144628117", new BotConfiguration() {{
            fileBasedDeviceInfo();
            setProtocol(MiraiProtocol.IPAD); // 切换协议
        }});
        System.out.println("开始登录!");
        bot.login();
        System.out.println("登录成功!");
        JavaMain.afterLogin(bot);
        System.out.println("事件已注册!");
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 1746703001;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });
    }
}


class WithConfiguration1 {
    public static void main(String[] args) {
        System.out.println( "密码长度:"+"yuan123456789".length());
        // 使用自定义配置
        System.out.println(111);
        Bot bot = BotFactory.INSTANCE.newBot(154592723, "yuan123456789", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.IPAD); // 切换协议

        }});
        System.out.println("开始登录!");
        System.out.println("getId:"+bot.getId()+ "   getNick:"+ bot.getNick());
        System.out.println();
        bot.login();
        System.out.println("登录成功!");
        afterLogin(bot);
        System.out.println("事件已注册!");
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 154592723;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });
    }
}
