package by.moiski.dao;

import by.moiski.dao.exceptions.DaoExeptions;

public interface IDao<T> {
	
	void saveOrUpdate(T t) throws DaoExeptions;
	
	T get() throws DaoExeptions;
	
	T load() throws DaoExeptions;
	
	void delete(T t) throws DaoExeptions;

}
