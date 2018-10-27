package logmonitor.bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import logmonitor.domain.Message;
import logmonitor.utils.MonitorHandler;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Describe: 过滤规则信息
 * Author:   maoxiangyi
 * Domain:   www.itcast.cn
 * Data:     2015/11/11.
 */
//BaseRichBolt 需要手动调ack方法，BaseBasicBolt由storm框架自动调ack方法
public class FilterBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 4403082848102076593L;
    private static Logger logger = Logger.getLogger(FilterBolt.class);
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        //获取KafkaSpout发送出来的数据
        String line = input.getString(0);
//        获取kafka发送的数据，是一个byte数组
//        byte[] value = (byte[]) input.getValue(0);
//        将数组转化成字符串
//        String line = new String(value);
        //对数据进行解析
        // appid   content
        //aid:1||msg:error java.lang.StackOverflowError
        Message message = MonitorHandler.parser(line);
        if (message == null) {
            return;
        }
        //判断规则，成功之后，将message对象发送的下游。
        //此时的message对象中包含：line,appId,ruleId,keyword
        if (MonitorHandler.trigger(message)) {
            collector.emit(new Values(message.getAppId(), message));
        }

        //定时更新规则信息 每十分钟更新一次规则库
        MonitorHandler.scheduleLoad();
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("appId", "message"));
    }
}
