package com.example.msasample.mono.travelreservation.util;

import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Springの管理下にないクラスからSpring管理オブジェクトを取得するクラス。
 * <br>
 * <br>
 * <code>
 * Foo foo = SpringContext.getBean(Foo.class);
 * </code>
 * 
 * @author ootsuka
 *
 */
@Component
public class SpringContext implements ApplicationContextAware {

	private static Optional<ApplicationContext> applicationContext = Optional.empty();

	/**
	 * Springの管理下にないクラスからSpring管理オブジェクトを取得する。
	 * <br>
	 * <br>
	 * <code>
	 * Foo foo = SpringContext.getBean(Foo.class);
	 * </code>
	 * 
	 * @author ootsuka
	 * @param clazz 取得したいオブジェクトのクラス。
	 *
	 */
	public static <T extends Object> T getBean(Class<T> clazz) {
		return applicationContext.map(c -> c.getBean(clazz)).orElse(null);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if (applicationContext.isEmpty()) {
			applicationContext = Optional.of(context);
		}

	}

}
