package com.bugjc.java.basics.annotation.extend;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ReflectUtils {
    private ReflectUtils() {
    }


    public static Type[] getActualTypeArguments(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            return pt.getActualTypeArguments();
        }
        return new Type[]{type};
    }




    public static boolean isPrimitive(Class type) {
        Objects.requireNonNull(type);
        if (type.isPrimitive() || String.class.equals(type)) {
            return true;
        }
        try {
            type.getDeclaredField("TYPE");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    public static Object invokeMethod(Method method, Object instance, Object... args) {
        try {
            Objects.requireNonNull(method);
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (Exception e) {
            throw new ReflectException("执行 " + instance + " 的 " + method + " 方法时发生异常,实参为: " + Arrays.toString(args), e);
        }
    }

    public static Object invokeMethod(Object instance, String methodeName, Object... args) {
        Class[] parameterTypes = new Class[args.length];
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
        }
        return invokeMethod(instance,methodeName, parameterTypes,args);
    }
    public static Object invokeMethod(Object instance, String methodeName,Class[] parameterTypes, Object... args) {
        Class instanceClass = Objects.isNull(instance)? null : instance.getClass();
        Method method =  getMethod(instanceClass, methodeName, parameterTypes);
        return invokeMethod(method, instance, args);
    }
    public static Object getFieldValue(Field field, Object instance) {
        try {
            Objects.requireNonNull(field);
            field.setAccessible(true);
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    public static void setFieldValue(Field field, Object instance, Object value) {
        try {
            Objects.requireNonNull(field);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... args) {
        try {
            Objects.requireNonNull(constructor);
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }


    public static <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
    public static <T> T newInstance(Class<T> type, Object... args) {
        return newInstance(type,null,args);
    }
    public static <T> T newInstance(Class<T> type, Class[] argTypes, Object... args) {
        if (needAutoParserTypes(argTypes,args)) {
            argTypes = getComponentTypes(args);
        }

        Constructor<T> constructor = ReflectUtils.getConstructor(type, argTypes);
        return newInstance(constructor, args);
    }
    public static boolean needAutoParserTypes(Class[] argTypes, Object... args){
        return (args != null && args.length != 0) && (argTypes == null || argTypes.length != args.length);
    }

    public static Class[] getComponentTypes(Object... args) {
        if (args == null || args.length == 0) {
            return new Class[0];
        }

        Class[] classes = new Class[args.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = args[i].getClass();
        }
        return classes;
    }

    public static Method getMethod(Class type, String methodName, boolean isSearchSuper, Class... parameterTypes) {
        try {
            return type.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class superclass = type.getSuperclass();
            if (!isSearchSuper || superclass == null || superclass.equals(Object.class)) {
                throw new ReflectException(e);
            }
            AtomicReference<Method> methodAtomic = new AtomicReference<>();
            forEachSuperclass(superclass, t -> {
                try {
                    methodAtomic.set(t.getDeclaredMethod(methodName, parameterTypes));
                    return true;
                } catch (NoSuchMethodException ex) {
                    return false;
                }
            });
            Method method = methodAtomic.get();
            if (method == null) {
                throw new ReflectException(e);
            }
            return method;
        }
    }

    public static Method getMethod(Class type, String methodName, Class... parameterTypes) {
        return getMethod(type, methodName, false, parameterTypes);
    }

    public static Field getField(Class type, String fieldName, boolean isSearchSuper) {
        try {
            return type.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superclass = type.getSuperclass();
            if (!isSearchSuper || superclass == null || superclass.equals(Object.class)) {
                throw new ReflectException(type.getName(), e);
            }
            AtomicReference<Field> fieldAtomic = new AtomicReference<>();
            forEachSuperclass(superclass, t -> {
                try {
                    fieldAtomic.set(t.getDeclaredField(fieldName));
                    return true;
                } catch (NoSuchFieldException ex) {
                    return false;
                }
            });
            Field field = fieldAtomic.get();
            if (field == null) {
                throw new ReflectException(type.getName(), e);
            }
            return field;
        }
    }

    public static Field getField(Class type, String fieldName) {
        return getField(type, fieldName, false);
    }

    public static void forEachField(Class type, Predicate<Field> predicate) {
        forEachSuperclass(type, c -> {
            Field[] fields = c.getDeclaredFields();
            boolean isBreak = false;
            for (Field field : fields) {
                isBreak = predicate.test(field);
                if (isBreak) {
                    break;
                }
            }
            return isBreak;
        });
    }

    public static void forEachMethod(Class type, Predicate<Method> predicate) {
        forEachSuperclass(type, c -> {
            Method[] methods = c.getDeclaredMethods();
            boolean isBreak = false;
            for (Method method : methods) {
                isBreak = predicate.test(method);
                if (isBreak) {
                    break;
                }
            }
            return isBreak;
        });
    }

    public static void forEachSuperclass(Class type, Predicate<Class> predicate) {
        Objects.requireNonNull(type);
        do {

            if (predicate.test(type)) {
                break;
            }
        } while ((type = type.getSuperclass()) != Object.class && type != null);
    }

    public static <T> Constructor<T> getConstructor(Class<T> targetClass, Class... parameterTypes) {
        try {
            return targetClass.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        }
    }

    public static Constructor getConstructor(Class targetClass) {
        return getConstructor(targetClass, new Class[0]);
    }

    public static <T> void forEachArray(T[] array, Consumer<T> consumer) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException(array + " 不是数组类型");
        }
        try {
            for (int i = 0; true; i++) {
                consumer.accept((T) Array.get(array, i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public static Object collection2Array(Collection collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(collection + " collection为空");
        }
        return array2Array(collection.toArray());
    }

    public static Object[] array2Array(Object[] fromArray) {
        if (fromArray.length < 1) {
            throw new IllegalArgumentException(fromArray + " 数组为空");
        }
        Class componentType = fromArray[0].getClass();
        Object toArray = Array.newInstance(componentType, fromArray.length);
        array2Array(toArray, fromArray);
        return (Object[]) toArray;
    }

    public static void collection2Array(Object array, Collection collection) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException(array + " 不是数组类型");
        }
        array2Array(array, collection.toArray());
    }

    public static void array2Array(Object toArray, Object[] fromArray) {
        if (!toArray.getClass().isArray()) {
            throw new IllegalArgumentException(toArray + " 不是数组类型");
        }
        System.arraycopy(fromArray, 0, toArray, 0, fromArray.length);
    }

    public static boolean isCollection(Class type) {
        return Collection.class.isAssignableFrom(type);
    }

    public static boolean isMap(Class type) {
        return Map.class.isAssignableFrom(type);
    }


}
