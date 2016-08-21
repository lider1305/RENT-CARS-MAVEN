package by.pvt.interfaces;

import by.pvt.exception.DAOException;
import by.pvt.entity.Client;

import java.util.List;

public interface IClientDAO extends IDAO<Client> {
    @Override
    void create(Client o) throws DAOException;

    @Override
    Client getId(long id) throws DAOException;

    @Override
    void update(Client object) throws DAOException;

    @Override
    void delete(long id) throws DAOException;

    @Override
    List getAll() throws DAOException;

    /**
     * метод лля авторизации пользователя в системе
     *
     * @param email    - email-логин для входа в систему
     * @param password - пароль для входа в систему
     */
    Client enterToSystem(String email, String password) throws DAOException;

    /**
     * метод возвращает пользователю забытый пароль по логину(email)
     *
     * @param email- логин для входа в систему
     * @return пароль для входа в систему
     */
    String forgotPassword(String email) throws DAOException;
}
