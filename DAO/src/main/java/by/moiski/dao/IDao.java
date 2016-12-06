package by.moiski.dao;

import java.io.Serializable;

import by.moiski.dao.exceptions.DaoExeptions;

public interface IDao<T> {
	
	void saveOrUpdate(T t) throws DaoExeptions;
	
	T get(Serializable id) throws DaoExeptions;
	
	T load(Serializable id) throws DaoExeptions;
	
	void delete(T t) throws DaoExeptions;

}
