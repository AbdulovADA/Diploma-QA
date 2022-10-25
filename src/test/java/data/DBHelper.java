package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;



public class DBHelper {
    @SneakyThrows
    public static void clearDB() {

        val deleteOrder = "DELETE FROM order_entity;";
        val deletePayment = "DELETE FROM payment_entity;";
        val deleteCredit = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            runner.update(conn, deleteOrder);
            runner.update(conn, deletePayment);
            runner.update(conn, deleteCredit);

        }
    }

    @SneakyThrows
    public static String getCreditStatusDB() {
        val status = "SELECT status FROM credit_request_entity;";
        val runner = new QueryRunner();
        String creditStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            creditStatus = runner.query(conn, status, new ScalarHandler<>());
        }

        return creditStatus;
    }

    @SneakyThrows
    public static String getPaymentStatusDB() {
        val sql = "SELECT status FROM payment_entity;";
        val runner = new QueryRunner();
        String payStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            payStatus = runner.query(conn, sql, new ScalarHandler<>());
        }

        return payStatus;
    }

    @SneakyThrows
    public static long getPaymentCount() {
        val sql = "SELECT COUNT(id) as count FROM payment_entity;";
        val runner = new QueryRunner();
        long payCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            payCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return payCount;
    }

    @SneakyThrows
    public static long getCreditCount() {
        val sql = "SELECT COUNT(id) as count FROM credit_request_entity;";
        val runner = new QueryRunner();
        long creditCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            creditCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return creditCount;
    }

    @SneakyThrows
    public static long getOrderCount() {
        val sql = "SELECT COUNT(id) as count FROM order_entity;";
        val runner = new QueryRunner();
        long orderCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass")
                );
        ) {
            orderCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return orderCount;
    }
}