package com.xfeng.plugin.generator.method;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class NewInsertSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method(introspectedTable.getInsertSelectiveStatementId());

        method.setReturnType(new FullyQualifiedJavaType("Integer"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getInsertSelectiveStatementId());
        method.setAbstract(true);

        FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record")); //$NON-NLS-1$

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

}
