package com.xfeng.mybatis.generator.codegen;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.sql.Types;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

public class MyMyBatis3FormattingUtilities {

    /**
     * Gets the parameter clause.
     *
     * @param introspectedColumn the introspected column
     * @return the parameter clause
     */
    public static String getParameterClause(IntrospectedColumn introspectedColumn) {
        return getParameterClause(introspectedColumn, null);
    }

    /**
     * Gets the parameter clause.
     *
     * @param introspectedColumn the introspected column
     * @param prefix             the prefix
     * @return the parameter clause
     */
    public static String getParameterClause(IntrospectedColumn introspectedColumn, String prefix) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{");
        sb.append(introspectedColumn.getJavaProperty(prefix));

        if (introspectedColumn.getJdbcTypeName().equalsIgnoreCase("VARBINARY")) {
            sb.append(",javaType=encryptBinary");
        }

        sb.append(",jdbcType=");
        sb.append(introspectedColumn.getJdbcTypeName());

        if (stringHasValue(introspectedColumn.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(introspectedColumn.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }

    public static XmlElement getIsNotNullElement(IntrospectedColumn introspectedColumn) {
        XmlElement isNotNullElement = new XmlElement("if");
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        if (introspectedColumn.isStringColumn() || introspectedColumn.getJdbcType() == Types.VARBINARY) {
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null and ");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != ''");
        } else {
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
        }
        isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
        return isNotNullElement;
    }

    public static XmlElement getIsNotNullElement(IntrospectedColumn introspectedColumn, String prefix) {
        XmlElement isNotNullElement = new XmlElement("if");
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        if (introspectedColumn.isStringColumn() || introspectedColumn.getJdbcType() == Types.VARBINARY) {
            sb.append(introspectedColumn.getJavaProperty(prefix));
            sb.append(" != null and ");
            sb.append(introspectedColumn.getJavaProperty(prefix));
            sb.append(" != ''");
        } else {
            sb.append(introspectedColumn.getJavaProperty(prefix));
            sb.append(" != null");
        }
        isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
        return isNotNullElement;
    }

    public static boolean isIgnoreColumn(String columnName, String answerName) {
        return ("insert".equals(answerName) && ("update_time".equals(columnName) || "update_user".equals(columnName))) || ("update".equals(answerName) && ("create_time".equals(columnName) || "create_user".equals(columnName)));
    }
}
