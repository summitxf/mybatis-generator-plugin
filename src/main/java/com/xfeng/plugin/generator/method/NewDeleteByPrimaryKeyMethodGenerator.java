package com.xfeng.plugin.generator.method;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class NewDeleteByPrimaryKeyMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method(introspectedTable.getDeleteByPrimaryKeyStatementId());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.setName(introspectedTable.getDeleteByPrimaryKeyStatementId());
        method.setAbstract(true);

        // no primary key class - fields are in the base class
        // if more than one PK field, then we need to annotate the
        // parameters
        // for MyBatis
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        boolean annotate = introspectedColumns.size() > 1;
        if (annotate) {
            importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        }
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
            importedTypes.add(type);
            Parameter parameter = new Parameter(type, introspectedColumn.getJavaProperty());
            if (annotate) {
                sb.setLength(0);
                sb.append("@Param(\"");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append("\")");
                parameter.addAnnotation(sb.toString());
            }
            method.addParameter(parameter);
        }

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

}
