import java.math.BigInteger;

public class GeneratorOGRNIP {

    public String ogrnIP() {
        int sign = (3); //Признак отнесения государственного регистрационного номера
        int year = (int) (10 + Math.random() * 10); //Год регистрации
        int region = (int) (10 + Math.random() * 75); // Регион регистрации
        int number = (int) (111111111 + Math.random() * 777777777); //Номер записи регистрации
        StringBuilder ogrnIP = new StringBuilder();
        for (var i : new int[]{sign, year, region, number}) {
            String s = String.valueOf(i);
            ogrnIP.append(s);
        }
        //контрольное число: младший разряд остатка от деления предыдущего 14-значного числа на 13,
        // если остаток от деления равен 10, то контрольное число равно 0 (нулю).
        BigInteger countControlNumber = (BigInteger.valueOf(Long.parseLong(ogrnIP.toString()))).mod(BigInteger.valueOf(13));
        int controlNumber = countControlNumber.intValue();
        controlNumber = (controlNumber == 10) ? 0 : controlNumber;
        return ogrnIP + String.valueOf(controlNumber);
    }
}
/*
----- СТРУКТУРА ОГРНИП -----
Регистрационный номер состоит из 15 знаков:

С ГГ КК ХХХХХХХХХ Ч

С (1-й знак) - признак отнесения государственного регистрационного номера записи:
к ОГРНИП - "3";
ГГ (2-й и 3-й знаки) - две последние цифры года внесения записи в государственный реестр, например для записи в 2016 году это "16";
КК (4-й и 5-й знаки) - кодовое обозначение субъекта Российской Федерации, установленное ФНС;
Х - Х (6-й - 14-й знаки) - номер записи, внесенной в государственный реестр в течение года;
Ч (15-й знак) - контрольное число: младший разряд остатка от деления предыдущего 14-значного числа на 13.
 */