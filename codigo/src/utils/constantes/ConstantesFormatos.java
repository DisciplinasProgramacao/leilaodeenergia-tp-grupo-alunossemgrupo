package utils.constantes;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@UtilityClass
public class ConstantesFormatos {

    public static final DateTimeFormatter FORMATO_DATA = ofPattern("dd-MM-yyyy HH:mm:ss");
}
