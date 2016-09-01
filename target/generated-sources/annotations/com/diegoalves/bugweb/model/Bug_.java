package com.diegoalves.bugweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bug.class)
public abstract class Bug_ {

	public static volatile SingularAttribute<Bug, String> severity;
	public static volatile SingularAttribute<Bug, Project> project;
	public static volatile SingularAttribute<Bug, String> descrption;
	public static volatile SingularAttribute<Bug, Long> Id;

}

