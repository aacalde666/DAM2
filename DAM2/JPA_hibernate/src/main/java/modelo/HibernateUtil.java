package modelo;

//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.io.File;

import org.hibernate.SessionFactory;


public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  
  static {
    try {
      sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
    } catch (Throwable ex) {
      // Log the exception. 
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
