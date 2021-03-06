package com.hopiestra.site.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.hopiestra.site.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.hopiestra.site.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Language.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.InternationalArticle.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.InternationalArticle.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Tag.class.getName() + ".internationalsArticle", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Theme.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.ThemeSubscription.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.ThemeSubscription.class.getName() + ".themes", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Theme.class.getName() + ".themeSubscriptions", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Article.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Article.class.getName() + ".internationalsArticles", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Commentary.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Article.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Tag.class.getName() + ".articles", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Tag.class.getName() + ".internationalTags", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.InternationalTag.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.InternationalTag.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Image.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.InternationalTheme.class.getName(), jcacheConfiguration);
            cm.createCache(com.hopiestra.site.domain.Theme.class.getName() + ".internationalThemes", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
