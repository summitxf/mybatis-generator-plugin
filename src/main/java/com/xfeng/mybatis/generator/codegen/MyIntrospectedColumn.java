package com.xfeng.mybatis.generator.codegen;

import org.mybatis.generator.api.IntrospectedColumn;

import java.sql.Types;

public class MyIntrospectedColumn extends IntrospectedColumn {

    public boolean isBLOBColumn() {
        String typeName = getJdbcTypeName();

        return "BINARY".equals(typeName) || "BLOB".equals(typeName) || "CLOB".equals(typeName)
                || "LONGNVARCHAR".equals(typeName) || "LONGVARBINARY".equals(typeName) || "LONGVARCHAR".equals(typeName)
                || "NCLOB".equals(typeName);
    }

    public boolean isJdbcCharacterColumn() {
        return jdbcType == Types.CHAR || jdbcType == Types.CLOB || jdbcType == Types.LONGVARCHAR
                || jdbcType == Types.VARCHAR || jdbcType == Types.LONGNVARCHAR || jdbcType == Types.NCHAR
                || jdbcType == Types.NCLOB || jdbcType == Types.NVARCHAR;
    }
}
