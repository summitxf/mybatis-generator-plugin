package com.xfeng.mybatis.generator.plugins;

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
        String javaModelPackage = null;
        try {
            javaModelPackage = (String) invoke(introspectedTable, IntrospectedTable.class, "calculateJavaModelPackage");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

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

    private Object invoke(final Object bean, Class clazz, final String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = clazz.getDeclaredMethod(name);
        method.setAccessible(true);
        return method.invoke(bean);
    }
}
