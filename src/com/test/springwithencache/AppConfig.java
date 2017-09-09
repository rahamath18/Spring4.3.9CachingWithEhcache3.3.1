package com.test.springwithencache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;


@Configuration
@EnableCaching
@ComponentScan({ "com.test.*" })
public class AppConfig {

	 public static void main(String[] args) {
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class,
						String.class, ResourcePoolsBuilder.heap(10)))
				.build();
		cacheManager.init();

		Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

		Cache<Long, String> myCache = cacheManager.createCache("myCache", CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));

		myCache.put(1L, "da one!");
		String value = myCache.get(1L);

		cacheManager.removeCache("preConfigured");

		cacheManager.close();
		 
	}
	 
}