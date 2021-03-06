package online.mrsys.movierecommender.dao.impl;

import java.util.List;

import online.mrsys.common.dao.impl.BaseDaoHibernate;
import online.mrsys.movierecommender.dao.UserDao;
import online.mrsys.movierecommender.domain.User;

public class UserDaoHibernate extends BaseDaoHibernate<User> implements UserDao {
    
    @Override
    public User findById(Integer id) {
        List<User> users = find("select u from User u where u.id = ?0", id);
        if (users != null && users.size() >= 1)
            return users.get(0);
        return null;
    }

	@Override
	public List<User> findByAccountAndPass(User user) {
		return find("select u from User u where u.account = ?0 and u.password = ?1", user.getAccount(), user.getPassword());
	}

	@Override
	public User findByAccount(String account) {
		List<User> users = find("select u from User u where u.account = ?0", account);
		if (users != null && users.size() >= 1)
			return users.get(0);
		return null;
	}

    @Override
    public User findByEmail(String email) {
        List<User> users = find("select u from User u where u.email = ?0", email);
        if (users != null && users.size() >= 1)
            return users.get(0);
        return null;
    }

}
