package ru.job4j.io;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.driver_class"),is("org.postgresql.Driver"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenValueNull() {
        String path = "./data/pair_with__value_null.properties";
        Config config = new Config(path);
        config.load();
    }
}
