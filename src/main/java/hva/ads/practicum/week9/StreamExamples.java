package hva.ads.practicum.week9;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Examples using Stream.generate()
 *
 * @author m.smithhva.nl
 */
public class StreamExamples {

    private static JdbcTemplate jdbcTemplate;

    // Batch size retrieval of database tuples.
    private static final int limit = 10;

    public static void main(String[] args) {
        sampleRandomNumbers();
        sampleDatabaseUntilNoMoreElements();
        sampleDatabaseKnowingNumberOfEntries();
    }

    // Throw a dice 100000 times and register the times a number was thrown.
    public static void sampleRandomNumbers() {
        Map<Integer, Long> occurence = Stream.generate(Math::random)
                .limit(100000)
                .map(d -> 6 * d + 1)
                .map(Double::intValue)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        occurence.forEach(((n, o) -> System.out.printf("Number %d occurence %d.\n", n, o)));
    }

    // Sample a databse until there are no more entries.
    private static void sampleDatabaseUntilNoMoreElements() {
        initializeJdbcTemplate();

        ObjectWrapper<Boolean> endOfStream = new ObjectWrapper<>(false);
        IntegerWrapper offset = new IntegerWrapper(BigInteger.ZERO);
        Stream.generate(() -> getCountriesWithEosSentinel(endOfStream, offset))
                .takeWhile(c -> !endOfStream.get())
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    // Sample a databse uknowing the number of entries to be retrieved.
    public static void sampleDatabaseKnowingNumberOfEntries() {
        initializeJdbcTemplate();

        IntegerWrapper offset = new IntegerWrapper(BigInteger.ZERO);
        Stream.generate(() -> getCountries(offset))
                .limit(getNumberOffCountries() / limit + 1)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    private static List<Country> getCountriesWithEosSentinel(ObjectWrapper<Boolean> endOfStream, IntegerWrapper offset) {
        List<Country> countries = getCountries(offset);
        endOfStream.set(countries.size() == 0);
        return countries;
    }

    private static List<Country> getCountries(IntegerWrapper offset) {
        System.out.printf("\tRetrieving %d entries from %s.\n", limit, offset);
        String query = "SELECT country_id as id, name, code FROM countries order by name limit ? offset ?";
        List<Country> countries = jdbcTemplate.query(query, new Object[]{limit, offset.get()}, new CountryRowMapper());
        offset.increaseAndGet(limit);
        return countries;
    }

    private static void initializeJdbcTemplate() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/msmith");
        config.setUsername(System.getProperty("user"));
        config.setDriverClassName("org.postgresql.Driver");
        config.setPassword(System.getProperty("password"));
        config.addDataSourceProperty("currentSchema", "sandbox");
        DataSource dataSource = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static int getNumberOffCountries() {
        String query = "SELECT count(*) FROM countries";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    private static class Country {
        private long id;

        private String name;

        private String code;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    private static class CountryRowMapper implements RowMapper<Country> {

        @Override
        public Country mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Country country = new Country();

            country.setId(resultSet.getInt("id"));
            country.setName(resultSet.getString("name"));
            country.setCode(resultSet.getString("code"));

            return country;
        }
    }

    private static class ObjectWrapper<T> {

        private T value;

        private ObjectWrapper(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public ObjectWrapper<T> set(T value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class IntegerWrapper extends ObjectWrapper<BigInteger> {

        private IntegerWrapper(BigInteger value) {
            super(value);
        }

        public ObjectWrapper<BigInteger> increaseAndGet() {
            return set(get().add(BigInteger.ONE));
        }

        public ObjectWrapper<BigInteger> increaseAndGet(long v) {
            return set(get().add(BigInteger.valueOf(v)));
        }
    }
}

