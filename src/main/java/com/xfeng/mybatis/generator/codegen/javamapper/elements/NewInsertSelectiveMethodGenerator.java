package com.xfeng.mybatis.generator.codegen.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class NewInsertSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method(introspectedTable.getInsertSelectiveStatementId());

        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getInsertSelectiveStatementId());
        method.setAbstract(true);

        FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

}
