package com.ishanitech.ipalika.dao;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jdbi.v3.core.mapper.JoinRow;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import com.ishanitech.ipalika.model.Role;
import com.ishanitech.ipalika.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */

@RegisterBeanMapper(value = User.class, prefix = "u")
@RegisterBeanMapper(value = Role.class, prefix = "r")
public interface UserDAO {
	
	@GetGeneratedKeys
	@SqlUpdate("INSERT into user (username, first_name, middle_name, last_name, email, password, enabled, locked) VALUES(:username, :firstName, :middleName, :lastName, :email, :password, :enabled, :locked)")
	public int addUser(@BindBean User user);

	@SqlUpdate("UPDATE user set username = :username, first_name = :firstName, middle_name = :middleName, last_name = :lastName, email = :email, password = :password, enabled = :enabled, locked = :locked WHERE id= :userId")
	public void updateUser(@BindBean User user, @Bind("userId") int userId);

	@SqlUpdate("DELETE FROM user where id = :userId")
	public void deleteUser(@Bind("userId") int userId);
	
	@SqlQuery(" SELECT u.id AS u_id, "
			+ " username AS u_username, "
			+ " first_name AS u_first_name, "
			+ " middle_name AS u_middle_name, "
			+ " last_name AS u_last_name, "
			+ " password AS u_password, "
			+ " email AS u_email, "
			+ " mobile_number AS u_mobile_number, "
			+ " locked AS u_locked, "
			+ " first_login AS u_first_login, "
			+ " enabled AS u_enabled, "
			+ " expired AS u_expired, "
			+ " registered_date AS u_registered_date, "
			+ " r.id AS r_id, "
			+ " r.role as r_role FROM user u INNER JOIN user_role ur ON u.id = ur.user_id "
			+ " INNER JOIN role r ON r.id = ur.role_id "
			+ " WHERE u.username = :username AND u.enabled = 1")
	@UseRowReducer(UserReducer.class)
	public User getUserByUsername(@Bind("username") String username);
	
	@SqlQuery("SELECT u.*, r.id AS r_id, r.role AS r_role FROM user u INNER JOIN user_role ur ON u.id = ur.user_id "
			+ "INNER JOIN role r ON r.id = ur.role_id ")
	@UseRowReducer(UserReducer.class)
	public List<JoinRow> getAllUsers();
	
	@SqlQuery("SELECT u.*, r.id AS r_id, r.role AS r_role FROM user u INNER JOIN user_role ur ON u.id = ur.user_id "
			+ "INNER JOIN role r ON r.id = ur.role_id "
			+ "WHERE u.id = :userId")
	@UseRowReducer(UserReducer.class)
	public List<JoinRow> getUserByUserId(@Bind("userId") int userId);

	@SqlBatch("INSERT INTO user_role (user_id, role_id) VALUES (:userId, :role.id)")
	public void addUserRoles(@Bind("userId") int userId, @BindBean("role") Collection<Role> role);
	
	@SqlUpdate("INSERT INTO user_role (user_id, role_id) VALUES (:userId, :roleId)")
	public void addUserRole(@Bind("userId") int userId, @Bind("roleId") int roleId);

	@SqlUpdate("DELETE FROM user_role WHERE user_id = :userId")
	public void deleteUserRole(@Bind("userId") int userId);
	
	class UserReducer implements LinkedHashMapRowReducer<Integer, User> {

		@Override
		public void accumulate(Map<Integer, User> container, RowView rowView) {
			User user = container.computeIfAbsent(rowView.getColumn("u_id", Integer.class), id -> rowView.getRow(User.class));
			  if(rowView.getColumn("r_id", Integer.class) != null) {
				  Role role = rowView.getRow(Role.class);
				  user.getRole().add(role);
			  }
			 
		}
		
	}

}
