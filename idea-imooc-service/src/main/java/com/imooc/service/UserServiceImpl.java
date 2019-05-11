package com.imooc.service;

import com.imooc.pojo.MyUsers;
import mapper.MyUsersMapper;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hulincloud
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MyUsersMapper usersMapper;

    @Autowired
    private Sid sid;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        MyUsers user = new MyUsers();
        user.setUsername(username);
        MyUsers result = usersMapper.selectOne(user);

        return result == null ? false:true;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(MyUsers user) {
        String userId = sid.nextShort();
        user.setId(userId);
        usersMapper.insert(user);
    }
}
