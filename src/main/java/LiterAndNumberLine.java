public class LiterAndNumberLine implements Comparable{
    long PointNumber;//адрес строки в файле(номер байта с которого начнется считывание)
    String Liter;//считанная строка которому соответствует адрес строки в файле
    //используется для сортировки адресов строк перед сортировкой
    public LiterAndNumberLine(long pointNumber, String liter){
        Liter=liter;
        PointNumber=pointNumber;
    }
//переопределение условия сравнения для сортировки в списке
    @Override
    public int compareTo(Object literAndNumberLine) {
        if(((LiterAndNumberLine)literAndNumberLine).Liter.charAt(0)=='-' && Liter.charAt(0)=='-')
            return ((LiterAndNumberLine)literAndNumberLine).Liter.compareTo(Liter);
        return Liter.compareTo(((LiterAndNumberLine)literAndNumberLine).Liter);
    }
}
