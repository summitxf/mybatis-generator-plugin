package com.xfeng.mybatis.generator.codegen.javamapper;

import com.xfeng.mybatis.generator.codegen.javamapper.elements.*;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectAllMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class MyJavaMapperGenerator extends JavaMapperGenerator {

    public MyJavaMapperGenerator(String project) {
        super(project);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(rootInterface);
            interfaze.addSuperInterface(fullyQualifiedJavaType);
            interfaze.addImportedType(fullyQualifiedJavaType);
        }

        addInsertSelectiveMethod(interfaze);
        addInsertSelectiveListMethod(interfaze);

        addDeleteByPrimaryKeyMethod(interfaze);

        addUpdateByPrimaryKeySelectiveMethod(interfaze);

        addSelectByPrimaryKeyMethod(interfaze);
        addSelectBySelectiveMethod(interfaze);
        addSelectPagedBySelectiveMethod(interfaze);
        addSelectOneBySelectiveMethod(interfaze);
        addSelectAllMethod(interfaze);
        addCountOneBySelectiveMethod(interfaze);

        List<CompilationUnit> answer = new ArrayList<>();
        if (context.getPlugins().clientGenerated(interfaze, introspectedTable)) {
            answer.add(interfaze);
        }

        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }

    protected void addInsertSelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MyInsertSelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addInsertSelectiveListMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MyInsertSelectiveListMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MyDeleteByPrimaryKeyMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MyUpdateByPrimaryKeySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(true);
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addSelectBySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MySelectBySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addSelectPagedBySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MySelectPagedBySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addSelectOneBySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MySelectOneBySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectAllMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectAllMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addCountOneBySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new MyCountBySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

}