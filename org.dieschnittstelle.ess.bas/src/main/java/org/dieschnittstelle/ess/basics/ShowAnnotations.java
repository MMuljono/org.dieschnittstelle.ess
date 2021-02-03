package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;
import org.dieschnittstelle.ess.utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.dieschnittstelle.ess.basics.reflection.ReflectedStockItemBuilder.getAccessorNameForField;
import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		//Consumer consumer = new Consumer();
		// ... and let them consume
		//consumer.doShopping(collection.getStockItems());
	}

	/*
	 * TODO BAS2
	 */
	private static void showAttributes(Object consumable){
		try {
			Class klass = consumable.getClass();
			StringBuilder sb = new StringBuilder();
			Field[] attrs = klass.getDeclaredFields();
			for (int i = 0; i < attrs.length; i++) {
				Field attr = attrs[i];
				attr.setAccessible(true);
				if(attr.isAnnotationPresent(DisplayAs.class)){
					Annotation an = attr.getAnnotation(DisplayAs.class);
					DisplayAs newName = (DisplayAs) an;
					show("consumable  + attr + new name" + consumable + "    "+ an + "    "+ newName.value());
					if (i == (attrs.length-1)) {
						sb.append(newName.value() + ": " + attr.get(consumable) );
					} else {
						sb.append(" " + newName.value() + ": " + attr.get(consumable) + ", ");
					}
				} else {
					if (i == (attrs.length-1)) {
						sb.append(attr.getName() + ": " + attr.get(consumable));
					} else {
						sb.append(" " + attr.getName() + ": " + attr.get(consumable) + ", ");
					}
				}

			}
			String result = "{" + klass.getSimpleName() + " " + sb.toString() + "}";
			show(result);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch(NullPointerException e){
			throw new RuntimeException(e);
		}


		// TODO BAS2: create a string representation of consumable by iterating
		//  over the object's attributes / fields as provided by its class
		//  and reading out the attribute values. The string representation
		//  will then be built from the field names and field values.
		//  Note that only read-access to fields via getters or direct access
		//  is required here.

		// TODO BAS3: if the new @DisplayAs annotation is present on a field,
		//  the string representation will not use the field's name, but the name
		//  specified in the the annotation. Regardless of @DisplayAs being present
		//  or not, the field's value will be included in the string representation.
	}

}
