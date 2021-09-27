package br.com.cm.workshop.apicrud.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.Getter;

@Getter
public enum TipoBebida {
    ALCOOLICA("Alcoolica"),
    SUCOS("Sucos"),
    LACTEA("Lactea"),
    REFRIGERANTE("Refrigerante"),
    MINERAL("Mineral");

    private static final List<TipoBebida> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private String descricao;

    TipoBebida(String descricao){
        this.descricao = descricao;
    }

    public static TipoBebida randomTipoBebida()  {
      return VALUES.get(RANDOM.nextInt(SIZE));
    }
}