package com.xfeng.plugin.generator.element;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class NewCountBySelectiveElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");

        String fqjt = introspectedTable.getBaseRecordType();

        answer.addAttribute(new Attribute("id", "countBySelective"));
        answer.addAttribute(new Attribute("parameterType", fqjt));
        answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "sql_where"));
        answer.addElement(include);

        parentElement.addElement(answer);
    }

}
