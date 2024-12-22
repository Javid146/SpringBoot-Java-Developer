package com.cydeo.bootstrap;

import com.cydeo.model.AnotherHuman;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<AnotherHuman> createHumans() {

        List<AnotherHuman> anotherHumen = new ArrayList<>();
        anotherHumen.add(new AnotherHuman(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().numberBetween(18,55), new Faker().address().state()));
        anotherHumen.add(new AnotherHuman(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().numberBetween(18,55), new Faker().address().state()));
        anotherHumen.add(new AnotherHuman(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().numberBetween(18,55), new Faker().address().state()));
        anotherHumen.add(new AnotherHuman(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().numberBetween(18,55), new Faker().address().state()));
        anotherHumen.add(new AnotherHuman(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().numberBetween(18,55), new Faker().address().state()));

        return anotherHumen;
    }
}
