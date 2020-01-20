package com.ishanitech.ipalika.dao;
import java.util.Map;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.model.Role;
import com.ishanitech.ipalika.model.User;
/**
 * A DAO interface which holds all the CRUD operations that can be performed 
 * on an User entity or Table.
 * @author Umesh Bhujel
 * @since 1.0
 */

@RegisterBeanMapper(value = User.class, prefix = "u")
@RegisterBeanMapper(value = Role.class, prefix = "r")
public interface UserDAO {
	
	/**
	 * Inserts user into database.
	 * @param user User model object.
	 */
	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO user ( `first_name`,"
			+ " `middle_name`, "
			+ " `last_name`, "
			+ " `username`, "
			+ " `email`, "
			+ " `password`, "
			+ " `mobile_number`, "
			+ " `locked`, "
			+ " `first_login`, "
			+ " `enabled`, "
			+ " `expired`, "
			+ " `registered_date`) "
			+ " VALUES (:firstName, "
			+ " :middleName, "
			+ " :lastName, "
			+ " :username, "
			+ " :email, "
			+ " :password, "
			+ " :mobileNumber, "
			+ " :locked, "
			+ " :firstLogin, "
			+ " :enabled, "
			+ " :expired, "
			+ " :registeredDate)")
	public int addUser(@BindBean User user);
	
	/**
	 * Inserts currently inserted user's role into user_role table.
	 * @param userId
	 * @param roleId
	 */
	@SqlUpdate("INSERT INTO user_role(`user_id`, `role_id`) VALUES(:userId, :roleId)")
	public void addUserRole(@Bind("userId") int userId, @Bind("roleId") int roleId);
	
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
	public User getUserByUsername(@Bind("username") String username) throws UnableToExecuteStatementException;
	
	
	@Transaction
	default void addUserAndRole(User user) {
		int userId = addUser(user);
		addUserRole(userId, 2); //staff id is 2
	}
	

	/**
	 * {@code UserReducer } Custom Row Reducer class to reduce master detail rows.
	 * Mainly used for @SqlQuery annotation which uses joins to reduce master-details
	 * rows to one or more master-level objects.
	 * @author Umesh Bhujel
	 * @since 1.0
	 */
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
