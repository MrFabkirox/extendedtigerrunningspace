package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Quote extends Model {
	
	@Id
	public String id;
	
	@Constraints.Required
	@Constraints.MinLength(5)
	public String name;
	
	//@Constraints.Email
	//@Constraints.MinLength(5)

}
