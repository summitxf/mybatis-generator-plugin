package com.xfeng.plugin.generator.element;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import com.xfeng.plugin.NewMyBatis3FormattingUtilities;

public class NewSelectPagedBySelectiveElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");

        answer.addAttribute(new Attribute("id", "selectPagedBySelective"));
        answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

        answer.addAttribute(new Attribute("parameterType", "map"));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        answer.addElement(new TextElement(sb.toString()));

        answer.addElement(getBaseColumnListElement());
        if (introspectedTable.hasBLOBColumns()) {
            answer.addElement(new TextElement(","));
            answer.addElement(getBlobColumnListElement());
        }

        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement where = new XmlElement("where");
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = NewMyBatis3FormattingUtilities.getIsNotNullElement(introspectedColumn, "record.");
            where.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(" and ");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(NewMyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "record."));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(where);

        answer.addElement(new TextElement("limit #{startRow}, #{rowSize}"));

        parentElement.addElement(answer);
    }

}
