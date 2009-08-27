/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.powermock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.powermock.reflect.internal.WhiteboxImpl;

/**
 * Various utilities for accessing internals of a class. Basically a simplified
 * reflection utility intended for tests.
 * 
 * @deprecated Use {@link org.powermock.reflect.Whitebox} instead. This class
 *             may be removed in the next version of PowerMock.
 */
public class Whitebox {

	/**
	 * Convenience method to get a method from a class type without having to
	 * catch the checked exceptions otherwise required. These exceptions are
	 * wrapped as runtime exceptions.
	 * <p>
	 * The method will first try to look for a declared method in the same
	 * class. If the method is not declared in this class it will look for the
	 * method in the super class. This will continue throughout the whole class
	 * hierarchy. If the method is not found an {@link IllegalArgumentException}
	 * is thrown.
	 * 
	 * @param type
	 *            The type of the class where the method is located.
	 * @param methodName
	 *            The method names.
	 * @param parameterTypes
	 *            All parameter types of the method (may be <code>null</code>).
	 * @return A <code>java.lang.reflect.Method</code>.
	 * @throws IllegalArgumentException
	 *             If a method cannot be found in the hierarchy.
	 */
	public static Method getMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
		return WhiteboxImpl.getMethod(type, methodName, parameterTypes);
	}

	/**
	 * Create a new instance of a class without invoking its constructor.
	 * <p>
	 * No byte-code manipulation is needed to perform this operation and thus
	 * it's not necessary use the <code>PowerMockRunner</code> or
	 * <code>PrepareForTest</code> annotation to use this functionality.
	 * 
	 * @param <T>
	 *            The type of the instance to create.
	 * @param classToInstantiate
	 *            The type of the instance to create.
	 * @return A new instance of type T, created without invoking the
	 *         constructor.
	 */
	public static <T> T newInstance(Class<T> classToInstantiate) {
		return WhiteboxImpl.newInstance(classToInstantiate);
	}

	/**
	 * Convenience method to get a (declared) constructor from a class type
	 * without having to catch the checked exceptions otherwise required. These
	 * exceptions are wrapped as runtime exceptions. The constructor is also set
	 * to accessible.
	 * 
	 * @param type
	 *            The type of the class where the constructor is located.
	 * @param parameterTypes
	 *            All parameter types of the constructor (may be
	 *            <code>null</code>).
	 * @return A <code>java.lang.reflect.Constructor</code>.
	 */
	public static Constructor<?> getConstructor(Class<?> type, Class<?>... parameterTypes) {
		return WhiteboxImpl.getConstructor(type, parameterTypes);
	}

	/**
	 * Set the value of a field using reflection.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldName
	 *            the name of the field
	 * @param value
	 *            the new value of the field
	 */
	public static void setInternalState(Object object, String fieldName, Object value) {
		WhiteboxImpl.setInternalState(object, fieldName, value);
	}

	/**
	 * Set the value of a field using reflection. This method will traverse the
	 * super class hierarchy until the first field assignable to the
	 * <tt>value</tt> type is found. The <tt>value</tt> will then be assigned to
	 * this field.
	 * 
	 * @param object
	 *            the object to modify
	 * @param value
	 *            the new value of the field
	 */

	public static void setInternalState(Object object, Object value) {
		WhiteboxImpl.setInternalState(object, value);
	}

	/**
	 * Set the value of a field using reflection at at specific place in the
	 * class hierarchy (<tt>where</tt>). This first field assignable to
	 * <tt>object</tt> will then be set to <tt>value</tt>.
	 * 
	 * @param object
	 *            the object to modify
	 * @param value
	 *            the new value of the field
	 * @param where
	 *            the class in the hierarchy where the field is defined
	 */
	public static void setInternalState(Object object, Object value, Class<?> where) {
		WhiteboxImpl.setInternalState(object, value, where);
	}

	/**
	 * Set the value of a field using reflection. Use this method when you need
	 * to specify in which class the field is declared. This might be useful
	 * when you have mocked the instance you are trying to modify.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldName
	 *            the name of the field
	 * @param value
	 *            the new value of the field
	 * @param where
	 *            the class in the hierarchy where the field is defined
	 */
	public static void setInternalState(Object object, String fieldName, Object value, Class<?> where) {
		WhiteboxImpl.setInternalState(object, fieldName, value, where);
	}

	/**
	 * Set the value of a field using reflection. This method will traverse the
	 * super class hierarchy until the first field of type <tt>fieldType</tt> is
	 * found. The <tt>value</tt> will then be assigned to this field.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldType
	 *            the type of the field
	 * @param value
	 *            the new value of the field
	 */
	public static void setInternalState(Object object, Class<?> fieldType, Object value) {
		WhiteboxImpl.setInternalState(object, fieldType, value);
	}

	/**
	 * Set the value of a field using reflection at a specific location (
	 * <tt>where</tt>) in the class hierarchy. The <tt>value</tt> will then be
	 * assigned to this field. The first field matching the <tt>fieldType</tt>
	 * in the hierarchy will be set.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldType
	 *            the type of the field the should be set.
	 * @param value
	 *            the new value of the field
	 * @param where
	 *            which class in the hierarchy defining the field
	 */
	public static void setInternalState(Object object, Class<?> fieldType, Object value, Class<?> where) {
		WhiteboxImpl.setInternalState(object, fieldType, value, where);
	}

	/**
	 * Get the value of a field using reflection. This method will iterate
	 * through the entire class hierarchy and return the value of the first
	 * field named <tt>fieldName</tt>. If you want to get a specific field value
	 * at specific place in the class hierarchy please refer to
	 * {@link #getInternalState(Object, String, Class)}.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldName
	 *            the name of the field
	 */
	public static Object getInternalState(Object object, String fieldName) {
		return WhiteboxImpl.getInternalState(object, fieldName);
	}

	/**
	 * Get the value of a field using reflection. Use this method when you need
	 * to specify in which class the field is declared. This might be useful
	 * when you have mocked the instance you are trying to access.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldName
	 *            the name of the field
	 * @param where
	 *            which class the field is defined
	 */
	public static <T> T getInternalState(Object object, String fieldName, Class<?> where) {
		return WhiteboxImpl.getInternalState(object, fieldName, where);
	}

	/**
	 * Get the value of a field using reflection. Use this method when you need
	 * to specify in which class the field is declared. This might be useful
	 * when you have mocked the instance you are trying to access. Use this
	 * method to avoid casting.
	 * 
	 * @param <T>
	 *            the expected type of the field
	 * @param object
	 *            the object to modify
	 * @param fieldName
	 *            the name of the field
	 * @param where
	 *            which class the field is defined
	 * @param type
	 *            the expected type of the field
	 */
	public static <T> T getInternalState(Object object, String fieldName, Class<?> where, Class<T> type) {
		return WhiteboxImpl.getInternalState(object, fieldName, where);
	}

	/**
	 * Get the value of a field using reflection based on the fields type. This
	 * method will traverse the super class hierarchy until the first field of
	 * type <tt>fieldType</tt> is found. The value of this field will be
	 * returned.
	 * 
	 * @param object
	 *            the object to modify
	 * @param fieldType
	 *            the type of the field
	 */
	public static <T> T getInternalState(Object object, Class<T> fieldType) {
		return WhiteboxImpl.getInternalState(object, fieldType);

	}

	/**
	 * Get the value of a field using reflection based on the field type. Use
	 * this method when you need to specify in which class the field is
	 * declared. The first field matching the <tt>fieldType</tt> in
	 * <tt>where</tt> is the field whose value will be returned.
	 * 
	 * @param <T>
	 *            the expected type of the field
	 * @param object
	 *            the object to modify
	 * @param fieldType
	 *            the type of the field
	 * @param where
	 *            which class the field is defined
	 */
	public static <T> T getInternalState(Object object, Class<T> fieldType, Class<?> where) {
		return WhiteboxImpl.getInternalState(object, fieldType, where);
	}

	/**
	 * Invoke a private or inner class method. This might be useful to test
	 * private methods.
	 */
	public static synchronized Object invokeMethod(Object tested, String methodToExecute, Object... arguments) throws Exception {
		return WhiteboxImpl.invokeMethod(tested, methodToExecute, arguments);
	}

	/**
	 * Invoke a private or inner class method in cases where power mock cannot
	 * automatically determine the type of the parameters, for example when
	 * mixing primitive types and wrapper types in the same method. For most
	 * situations use {@link #invokeMethod(Class, String, Object...)} instead.
	 * 
	 * @throws Exception
	 *             Exception that may occur when invoking this method.
	 */
	public static synchronized Object invokeMethod(Object tested, String methodToExecute, Class<?>[] argumentTypes, Object... arguments)
			throws Exception {
		return WhiteboxImpl.invokeMethod(tested, methodToExecute, argumentTypes, arguments);
	}

	/**
	 * Invoke a private or inner class method in a subclass (defined by
	 * <code>definedIn</code>) in cases where power mock cannot automatically
	 * determine the type of the parameters, for example when mixing primitive
	 * types and wrapper types in the same method. For most situations use
	 * {@link #invokeMethod(Class, String, Object...)} instead.
	 * 
	 * @throws Exception
	 *             Exception that may occur when invoking this method.
	 */
	public static synchronized Object invokeMethod(Object tested, String methodToExecute, Class<?> definedIn, Class<?>[] argumentTypes,
			Object... arguments) throws Exception {
		return WhiteboxImpl.invokeMethod(tested, methodToExecute, definedIn, argumentTypes, arguments);
	}

	/**
	 * Invoke a private or inner class method in that is located in a subclass
	 * of the tested instance. This might be useful to test private methods.
	 * 
	 * @throws Exception
	 *             Exception that may occur when invoking this method.
	 */
	public static synchronized Object invokeMethod(Object tested, Class<?> declaringClass, String methodToExecute, Object... arguments)
			throws Exception {
		return WhiteboxImpl.invokeMethod(tested, declaringClass, methodToExecute, arguments);
	}

	/**
	 * Invoke a private or inner class method in that is located in a subclass
	 * of the tested instance. This might be useful to test private methods.
	 * <p>
	 * Use this for overloaded methods.
	 * 
	 * @throws Exception
	 *             Exception that may occur when invoking this method.
	 */
	public static synchronized Object invokeMethod(Object object, Class<?> declaringClass, String methodToExecute, Class<?>[] parameterTypes,
			Object... arguments) throws Exception {
		return WhiteboxImpl.invokeMethod(object, declaringClass, methodToExecute, parameterTypes, arguments);
	}

	/**
	 * Invoke a private or inner class method. This might be useful to test
	 * private methods.
	 * 
	 */
	public static synchronized Object invokeMethod(Class<?> clazz, String methodToExecute, Object... arguments) throws Exception {
		return WhiteboxImpl.invokeMethod(clazz, methodToExecute, arguments);
	}

	/**
	 * Invoke a constructor. Useful for testing classes with a private
	 * constructor when PowerMock cannot determine which constructor to invoke.
	 * This only happens if you have two constructors with the same number of
	 * arguments where one is using primitive data types and the other is using
	 * the wrapped counter part. For example:
	 * 
	 * <pre>
	 * public class MyClass {
	 *     private MyClass(Integer i) {
	 *         ...
	 *     } 
	 * 
	 *     private MyClass(int i) {
	 *         ...
	 *     }
	 * </pre>
	 * 
	 * This ought to be a really rare case. So for most situation, use
	 * {@link #invokeConstructor(Class, Object...)} instead.
	 * 
	 * 
	 * @return The object created after the constructor has been invoked.
	 * @throws Exception
	 *             If an exception occur when invoking the constructor.
	 */
	public static <T> T invokeConstructor(Class<T> classThatContainsTheConstructorToTest, Class<?>[] parameterTypes, Object[] arguments)
			throws Exception {
		return WhiteboxImpl.invokeConstructor(classThatContainsTheConstructorToTest, parameterTypes, arguments);
	}

	/**
	 * Invoke a constructor. Useful for testing classes with a private
	 * constructor.
	 * 
	 * 
	 * @return The object created after the constructor has been invoked.
	 * @throws Exception
	 *             If an exception occur when invoking the constructor.
	 */
	public static <T> T invokeConstructor(Class<T> classThatContainsTheConstructorToTest, Object... arguments) throws Exception {
		return WhiteboxImpl.invokeConstructor(classThatContainsTheConstructorToTest, arguments);
	}

	/**
	 * Get the first parent constructor defined in a super class of
	 * <code>klass</code>.
	 * 
	 * @param klass
	 *            The class where the constructor is located. <code>null</code>
	 *            ).
	 * @return A <code>java.lang.reflect.Constructor</code>.
	 */
	public static Constructor<?> getFirstParentConstructor(Class<?> klass) {
		return WhiteboxImpl.getFirstParentConstructor(klass);
	}

	/**
	 * Get an array of {@link Method}'s that matches the supplied list of method
	 * names.
	 * 
	 * @param clazz
	 *            The class that should contain the methods.
	 * @param methodNames
	 *            An array names of the methods that will be returned.
	 * @return An array of Method's. May be of length 0 but not
	 *         <code>null</code>.
	 */
	public static Method[] getMethods(Class<?> clazz, String... methodNames) {
		return WhiteboxImpl.getMethods(clazz, methodNames);
	}
}
