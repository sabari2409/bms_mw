package com.scaler.bms.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcedureManager implements CommandLineRunner {


    private final JdbcTemplate jdbcTemplate;

    public ProcedureManager(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=========== job started to execute =============");
//        this.jdbcTemplate.execute("CALL init_master_data()");
//        this.jdbcTemplate.execute("CALL insert_theatre_proc()");
//        this.jdbcTemplate.execute("CALL insert_auditorium_proc()");
//        this.jdbcTemplate.execute("CALL insert_seat_proc()");
//        this.jdbcTemplate.execute("CALL insert_movies_proc()");
//        this.jdbcTemplate.execute("CALL insert_show_proc()");
//        this.jdbcTemplate.execute("CALL insert_show_seats_proc()");
        System.out.println("============== job completed ================");
    }
}
