package org.dieschnittstelle.ess.basics;

import java.util.List;

public class ShowClasspathScan {

	public static void main(String[] args) {
		listLoadedClasses(ShowClasspathScan.class.getClassLoader());
	}
	
	public static void listLoadedClasses(ClassLoader byClassLoader) {
	    Class<?> clKlass = byClassLoader.getClass();
	    System.out.println("Classloader: " + clKlass.getCanonicalName());
	    while (clKlass != ClassLoader.class) {
	        clKlass = clKlass.getSuperclass();
	    }
	    try {
	        java.lang.reflect.Field fldClasses = clKlass
	                .getDeclaredField("classes");
	        fldClasses.setAccessible(true);
	        List<Class<?>> classes = (List<Class<?>>) fldClasses.get(byClassLoader);
	        for (Class<?> klass : classes) {
	            System.out.println("   Loaded " + klass);
	        }
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    }
	}
	
}

