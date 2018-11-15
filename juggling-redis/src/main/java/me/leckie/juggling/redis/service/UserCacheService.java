package me.leckie.juggling.redis.service;

import me.leckie.juggling.redis.domain.User;

/**
 * @author Leckie
 * @version $Id: UserCacheService.java, v0.1 2018/10/23 9:42 Leckie Exp $$
 */
public interface UserCacheService extends CacheService<String, User> {

}
