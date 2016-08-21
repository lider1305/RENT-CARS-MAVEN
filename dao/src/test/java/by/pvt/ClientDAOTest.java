package by.pvt;

import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Client;
import by.pvt.exception.DAOException;
import by.pvt.dao.impl.ClientDAO;
import by.pvt.util.DateFormatUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class ClientDAOTest {
    private ClientDAO clientDAO;
    private Client client;

    @Before
    public void setUp() throws Exception {
        clientDAO = ClientDAO.getInstance();
        client = new Client();
        client.setEmail("vanu@mail.ru");
        client.setName("Vania");
        client.setSurName("Ivanow");
        client.setPassword("1234");
        client.setPassport("HG222222");
        client.setPassportEndDate(DateFormatUtil.dateFormatterFromLongToDate(1465423200000l));
        client.setPassportIssueDate(DateFormatUtil.dateFormatterFromLongToDate(1465423200000l));
        client.setPhone("+3574554444444");
        client.setRole("USER");
        client.setStatus("NOT ACTIVE");
    }

    @Test
    public void getInstance() throws Exception {
        ClientDAO client1 = ClientDAO.getInstance();
        ClientDAO client2 = ClientDAO.getInstance();
        Assert.assertEquals(client1, client2);
    }

    @Test
    public void create() throws Exception {
        clientDAO.createPassport(client);
        clientDAO.getPassport(client);
        clientDAO.create(client);
        long clientId = clientDAO.getMaxClintId();
        client.setClientID(clientId);
        Client actual = clientDAO.getId(clientId);
        Assert.assertEquals(client, actual);
    }

    @Test
    public void getId() throws Exception {
        Client clientInBase = clientDAO.getId(clientDAO.getMaxClintId());
        client.setClientID(clientDAO.getMaxClintId());
        Assert.assertEquals(client, clientInBase);
    }

    @Test
    public void testUpdate() throws Exception {
        clientDAO.createPassport(client);
        clientDAO.getPassport(client);
        clientDAO.create(client);
        long clientId = clientDAO.getMaxClintId();
        client.setClientID(clientId);
        Client actual = clientDAO.getId(clientId);
        actual.setPassport("jj22222");
        actual.setName("Dima");
        actual.setPhone("+3333333333");
        actual.setSurName("Timurov");
        clientDAO.update(actual);
        System.out.println(client);
        System.out.println(actual);
        Assert.assertNotSame(client, actual);
        deleteClient(clientId);
        deleteClientPassport(actual.getPassport());
    }

    @Test
    public void enterToSystem() throws Exception {
        Client client1 = clientDAO.enterToSystem(client.getEmail(), client.getPassword());
        Assert.assertEquals(client1.getEmail(), client.getEmail());
        Assert.assertEquals(client1.getPassword(), client.getPassword());
    }

    @Test
    public void forgotPassword() throws Exception {
        String pas = clientDAO.forgotPassword(client.getEmail());
        Assert.assertEquals(pas, client.getPassword());
    }

    @Test
    public void testDelete() throws Exception {
        long clientId = clientDAO.getMaxClintId();
        Client client1 = clientDAO.getId(clientId);
        clientDAO.delete(clientId);
        Client client2 = clientDAO.getId(clientId);
        Assert.assertNotSame(client1.getStatus(), client2.getStatus());
        deleteClient(clientId);
        deleteClientPassport(client.getPassport());

    }

    @Test
    public void getAll() throws Exception {
        List list = clientDAO.getAll();
        long size = list.size();
        Assert.assertEquals(clientDAO.getAllClientsId(), size);
    }


    public static void deleteClient(long id) throws DAOException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM CLIENTS WHERE CLIENT_ID =?")) {

            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
        } catch (Exception e) {

            throw new DAOException("Unable to delete client from base!", e);
        }
    }

    public static void deleteClientPassport(String passport) throws DAOException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM PASSPORTS WHERE PASSPORTS_NUMBER =?")) {

            preparedStatement1.setString(1, passport);
            preparedStatement1.executeUpdate();
        } catch (Exception e) {

            throw new DAOException("Unable to delete passport from base!", e);
        }
    }

}