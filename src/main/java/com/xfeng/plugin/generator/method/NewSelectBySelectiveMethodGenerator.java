package com.xfeng.plugin.generator.method;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class NewSelectBySelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());

        Method method = new Method("selectBySelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setAbstract(true);

        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        importedTypes.add(listType);
        returnType.addTypeArgument(listType);
        method.setReturnType(returnType);
        method.setName("selectBySelective");
        method.addParameter(new Parameter(parameterType, "record"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }
}
