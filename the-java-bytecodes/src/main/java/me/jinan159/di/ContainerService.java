package me.jinan159.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

	public static <T> T getObject(Class<T> clazz) {
		T newInstance = getNewInstance(clazz);

		Arrays.stream(clazz.getDeclaredFields())
			.forEach(field -> {
				if (field.getAnnotation(Inject.class) != null) {
					Class<?> type = field.getType();
					Object fieldInstance = getNewInstance(type);
					field.setAccessible(true);
					try {
						field.set(newInstance, fieldInstance);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			});

		return newInstance;
	}

	private static <T> T getNewInstance(Class<T> clazz) {
		try {
			return clazz.getConstructor().newInstance();
		} catch (InstantiationException |
				 IllegalAccessException |
				 InvocationTargetException |
				 NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
