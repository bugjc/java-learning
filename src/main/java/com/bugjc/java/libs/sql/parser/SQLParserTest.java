package com.bugjc.java.libs.sql.parser;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import org.junit.jupiter.api.Test;

@Slf4j
public class SQLParserTest {


    @Test
    void jSqlParser() throws JSQLParserException {
        //Template
        Insert insertExpand = (Insert) CCJSqlParserUtil.parse("INSERT IGNORE INTO Placeholder1 (Placeholder2) values (Placeholder3)");
        log.info("Insert SQL:{}", insertExpand.toString());

        //setting a table name
        insertExpand.getTable().setName("T");
        //adding a column
        insertExpand.getColumns().add(new Column("col1"));
        //adding a value using a visitor
        ((ExpressionList) insertExpand.getItemsList()).getExpressions().add(new HexValue("?"));
        log.info("Insert SQL:{}", insertExpand.toString());

        //removing a column
        insertExpand.getColumns().remove(0);
        //removing a value using a visitor
        ((ExpressionList) insertExpand.getItemsList()).getExpressions().remove(0);
        log.info("Insert SQL:{}", insertExpand.toString());
    }
}
