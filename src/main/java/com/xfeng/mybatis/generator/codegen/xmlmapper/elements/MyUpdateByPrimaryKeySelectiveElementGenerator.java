package com.xfeng.mybatis.generator.codegen.xmlmapper.elements;

import com.xfeng.mybatis.generator.codegen.MyMyBatis3FormattingUtilities;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class MyUpdateByPrimaryKeySelectiveElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("update");

        answer.addAttribute(new Attribute("id", introspectedTable.getUpdateByPrimaryKeySelectiveStatementId()));

        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("set");
        answer.addElement(dynamicElement);

        for (IntrospectedColumn introspectedColumn : ListUtilities
                .removeGeneratedAlwaysColumns(introspectedTable.getNonPrimaryKeyColumns())) {

            if (MyMyBatis3FormattingUtilities.isIgnoreColumn(introspectedColumn.getActualColumnName(), answer.getName())) {
                continue;
            }

            XmlElement isNotNullElement = MyMyBatis3FormattingUtilities.getIsNotNullElement(introspectedColumn);

            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyMyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            sb.append(',');

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            if (MyMyBatis3FormattingUtilities.isIgnoreColumn(introspectedColumn.getActualColumnName(), answer.getName())) {
                continue;
            }

            sb.setLength(0);
            if (and) {
                sb.append(" and ");
            } else {
                sb.append("where ");
                and = true;
            }

            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyMyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }

        parentElement.addElement(answer);
    }

}
