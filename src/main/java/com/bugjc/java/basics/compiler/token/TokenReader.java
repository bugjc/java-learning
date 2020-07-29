package com.bugjc.java.basics.compiler.token;

/**
 * 一个Token流。由Lexer生成。Parser可以从中获取Token。
 * @author aoki
 * @date 2020/7/28
 * **/
public interface TokenReader {
    /**
     * 返回 Token 流中下一个 Token，并从流中取出。 如果流已经为空，返回 null;
     * @return Token
     */
    Token read();

    /**
     * 返回 Token 流中下一个 Token，但不从流中取出。 如果流已经为空，返回 null;
     * @return Token
     */
    Token peek();

    /**
     * Token 流回退一步。恢复原来的 Token。
     * @return Token
     */
    void unread();

    /**
     * 获取Token流当前的读取位置。
     * @return Token
     */
    int getPosition();

    /**
     * 设置 Token 流当前的读取位置
     *
     * @param position  --指定的位置
     */
    void setPosition(int position);

    /**
     * 打印所有 token
     */
    void dump();
}