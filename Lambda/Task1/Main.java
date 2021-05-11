/*Задача 1: Калькулятор*/
/*В ходн реализации метода apply лямда-выражения devide = (x, y) -> x / y из функционального интерфейса  BinaryOperator с типом Integer,
в ходе компиляции появляется ошибка деления на 0, так как параметры лямды-выражения имеют целочисленный тип.
Одним из способов решения является приведение абстрактных методов функциональных интерфейсов и лямбда-выражений к вещественному типу или
у лямбда-выражении devide = (x, y) -> x / y использовать тернарный оператор.
Перепишем следующим образом лямбда-выражении devide  = (x, y) ->  y != 0 ? x / y : -1.
При вызове вышеуказанного лямда-выражения если делитель не равен 0, происходит деление x на y, иначе возвращаем -1.
*/
package JavaCore.Lambda.Task1;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.devide.apply(a, b);
        calc.println.accept(c);
    }
}