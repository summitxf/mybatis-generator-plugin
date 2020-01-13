package com.xfeng.mybatis.generator.plugins;

import com.xfeng.mybatis.generator.codegen.NewIntrospectedTable;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

public class RenameJavaModelPlugins extends PluginAdapter {

    private String modelSuffix;

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

        String javaModelPackage =((NewIntrospectedTable)introspectedTable).calculateJavaModelPackage();
        StringBuilder sb = new StringBuilder();
        sb.append(javaModelPackage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Key");
        sb.append(this.modelSuffix);
        introspectedTable.setPrimaryKeyType(sb.toString());

        sb.setLength(0);
        sb.append(javaModelPackage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append(this.modelSuffix);
        introspectedTable.setBaseRecordType(sb.toString());

        sb.setLength(0);
        sb.append(javaModelPackage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("WithBLOBs");
        sb.append(this.modelSuffix);
        introspectedTable.setRecordWithBLOBsType(sb.toString());
    }

    @Override
    public boolean validate(List<String> warnings) {
        Properties properties = this.getProperties();

        this.modelSuffix = properties.getProperty("modelSuffix");

        return true;
    }

}
