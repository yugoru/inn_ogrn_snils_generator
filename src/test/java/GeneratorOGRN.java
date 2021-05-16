import java.math.BigInteger;
import java.util.Random;

public class GeneratorOGRN {

    public String ogrn() {
        int sign = (getRandom()); //Признак отнесения государственного регистрационного номера
        int year = (int) (10 + Math.random() * 10); //Год регистрации
        int region = (int) (10 + Math.random() * 75); // Регион регистрации
        int inspection = (int) (10 + Math.random() * 89); //Код налоговой инспекции
        int number = (int) (11111 + Math.random() * 77777); //Номер записи регистрации
        StringBuilder ogrn = new StringBuilder();
        for (var i : new int[]{sign, year, region, inspection, number}) {
            String s = String.valueOf(i);
            ogrn.append(s);
        }
        //контрольное число: младший разряд остатка от деления предыдущего 12-значного числа на 11,
        // если остаток от деления равен 10, то контрольное число равно 0 (нулю).
        BigInteger countControlNumber = (BigInteger.valueOf(Long.parseLong(ogrn.toString()))).mod(BigInteger.valueOf(11));
        int controlNumber = countControlNumber.intValue();
        controlNumber = (controlNumber == 10) ? 0 : controlNumber;
        return ogrn + String.valueOf(controlNumber);
    }

    static int getRandom() {
        int[] sign = {1, 5};
        return sign[new Random().nextInt(sign.length)];
    }
}
/*
----- СТРУКТУРА ОГРН -----
Государственный регистрационный номер записи, вносимой в Единый государственный реестр юридических лиц, состоит из 13 цифр, расположенных в следующей последовательности:

С Г Г К К Н Н Х Х Х Х Х Ч
где:
С (1-й знак) — признак отнесения государственного регистрационного номера записи:
к основному государственному регистрационному номеру (ОГРН)* — 1, 5 (присваивается юридическому лицу)
ГГ (со 2-го по 3-й знак) — две последние цифры года внесения записи в государственный реестр
КК (4-й, 5-й знаки) — порядковый номер субъекта Российской Федерации по перечню субъектов Российской Федерации, установленному статьей 65 Конституции Российской Федерации
НН (с 6-го по 7-й знак) — код налоговой инспекции
ХХХХХ (с 8-го по 12-й знак) — номер записи, внесенной в государственный реестр в течение года
Ч (13-й знак) — контрольное число: младший разряд остатка от деления предыдущего 12-значного числа на 11, если остаток от деления равен 10, то контрольное число равно 0 (нулю).
 */