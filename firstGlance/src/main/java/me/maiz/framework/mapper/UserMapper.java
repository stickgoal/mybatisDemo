package me.maiz.framework.mapper;

import me.maiz.framework.mapper.DO.User;


/**
 * Created by Lucas on 2016-11-10.
 */
public interface UserMapper {

        User selectUser(int userId);
}
