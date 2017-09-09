package com.test.springwithencache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class TestEncache331 {

	public static void main(String[] args) {

		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
		          .withCache("preConfigured",
		               CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
		                                              ResourcePoolsBuilder.heap(100))
		               .build())
		          .build(true);

		      Cache<Long, String> preConfigured
		          = cacheManager.getCache("preConfigured", Long.class, String.class);

		      Cache<Long, String> myCache = cacheManager.createCache("myCache",
		          CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
		                                        ResourcePoolsBuilder.heap(100)).build());

		      myCache.put(1L, "da one!");
		      String value = myCache.get(1L);
 
		      cacheManager.close();
		
	}

}
