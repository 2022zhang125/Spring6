1.在Spring中集成Log4j2日志框架
    自定义log日志
        // 拿到对应类的logger对象
        Logger logger = LoggerFactory.getLogger(Log4j2Test.class);
        // 通过该对象对日志进行填充
        logger.info()/logger.error()/logger.debug().....

2.彩色输出
    -Dlog4j.skipJansi=false