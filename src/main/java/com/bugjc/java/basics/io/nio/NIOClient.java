package com.bugjc.java.basics.io.nio;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.bugjc.java.basics.io.nio.handler.ClientReadHandler;
import com.bugjc.java.basics.io.nio.model.MsgPacket;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@Slf4j
public class NIOClient {
    /**
     * 空闲等待最长时间
     */
    private static final int MAX_IDLE_COUNT = 60;

    /**
     * 定义最大缓存区大小
     */
    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * 是否关闭客户端的标志
     */
    private boolean isClosed = false;

    /**
     * 通道管理器
     */
    private Selector selector;

    /**
     * 与服务端交互的socket通道
     */
    private SocketChannel socketChannel;

    /**
     * 分配的读缓存
     */
    private ByteBuffer readBuffer;

    /**
     * 读处理器
     */
    private ClientReadHandler readHandler;

    /**
     * 待写队列
     */
    private LinkedList<ByteBuffer> bufferQueue;

    /**
     * 当前空闲计数
     */
    private int idleCount;

    private String serverIp;
    private int port;
    private String name;

    public String getName() {
        return this.name;
    }

    public NIOClient() {
    }

    public NIOClient(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public NIOClient(String name, String serverIp, int port) {
        this.name = name;
        this.serverIp = serverIp;
        this.port = port;
    }

    /**
     * 初始化
     */
    private void init() {

        idleCount = 0;

        // 初始化读缓存
        readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        // 初始化待写队列
        bufferQueue = new LinkedList<ByteBuffer>();

        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            // 需要设置为非阻塞模式才能进行一系列操作
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(serverIp, port));
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 启动
    public void startup() {
        // 完成初始化
        init();

        new Thread(() -> {
            try {
                while (!isClosed) {
                    int nKey = selector.select(1000);    // 结合MAX_IDLE_COUNT 等价于 MAX_IDLE_COUNT(s)空闲时间检测
                    if (nKey > 0) {
                        idleCount = 0;
                        Set<SelectionKey> keySet = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = keySet.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            if (key.isConnectable()) {
                                // 连接事件
                                finishedConnection(key);
                            } else if (key.isReadable()) {
                                // 读事件
                                readFromChanel(key);
                            } else if (key.isWritable()) {
                                // 写事件
                                writeToChannel(key);
                            }
                        }
                    } else {
                        idleCount++;
                        if (idleCount >= MAX_IDLE_COUNT) {
                            // 空闲超时,断开与客户端的连接
                            close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void finishedConnection(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key
                .channel();
        if (socketChannel.isConnectionPending()) {
            try {
                socketChannel.finishConnect();
                socketChannel.configureBlocking(false);
                // 注册读权限
                socketChannel.register(selector,
                        SelectionKey.OP_READ);
                this.socketChannel = socketChannel;
                readHandler = new ClientReadHandler(this, this.socketChannel);
                log.info(DateUtil.now(), this.name, "{} {} {}  Connect to Server");

                final String msg = "本地服务器你好!" + "我是" + this.getName();
                final String msg1 = "我是" + this.getName() + " 本地服务器你好!";
                send(msg);
                send(msg1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromChanel(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        // 清空读缓存
        readBuffer.clear();
        try {
            int count = channel.read(readBuffer);
            if (count > 0) {
                byte[] data = new byte[count];
                System.arraycopy(readBuffer.array(), 0, data, 0, count);
                readHandler.read(data);
                readHandler.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socketChannel.close();
                key.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void close() {
        try {
            // 使线程退出
            isClosed = true;
            selector.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        MsgPacket msgPacket = new MsgPacket(msg);
        // 添加到写队列
        bufferQueue.add(ByteBuffer.wrap(msgPacket.getBytes()));
        socketChannel.keyFor(this.selector).interestOps(SelectionKey.OP_WRITE);
        // 唤醒
        selector.wakeup();
    }

    /**
     * 往通道写
     *
     * @param key
     */
    private void writeToChannel(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        while (!bufferQueue.isEmpty()) {
            ByteBuffer buffer = bufferQueue.get(0);
            try {
                socketChannel.write(buffer);
                if (buffer.remaining() > 0) {
                    break;
                }
                bufferQueue.remove(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bufferQueue.isEmpty()) {
            // 全部数据写完了 取消写等待事件
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) {
        NIOClient client = new NIOClient(RandomUtil.randomString(4),"127.0.0.1", 9000);
        client.startup();
        log.info("{} client startup", DateUtil.now());
    }
}
