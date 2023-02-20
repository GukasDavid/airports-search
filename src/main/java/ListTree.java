import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

public class ListTree {
    ArrayList<String> ResultFind;
    RandomAccessFile File;
    int column;
    public ListTree (RandomAccessFile file,int column){
        ResultFind=new ArrayList<String>();
        File=file;
        this.column=column;
    }

    //переписывает значения из динамического массива в статический массив для более быстрого считывания данных
    // при этом перед считыванием сортирует данные в массиве и после ссчитывания очищает динамический массив
    public void OptimisationTree(Item item){
        if(item.NumbersLine.size()>0) {
            item.arrNumbersLine = new Long[item.NumbersLine.size()];
            Collections.sort(item.NumbersLine);
            for (int i = 0; i < item.arrNumbersLine.length; i++) {
                item.arrNumbersLine[i] = item.NumbersLine.get(i).PointNumber;
            }
            item.NumbersLine.clear();
        }

        if(item.childs.size()==0)
            return;
        for(char key: item.childs.keySet())
            OptimisationTree(item.childs.get(key));
    }

    //рекурсивная функция для заполнения дерево поиска
    public void rekursTree(long pointLine, String liter,Item item,int step){

        //записываем текущую строчку и считаное слово
        item.NumbersLine.add(new LiterAndNumberLine(pointLine,liter));
        char key = liter.charAt(step);

        //условие выхода из рекурсии
        if (step == 4)
            return;
        //создаем новый узел с ключом key
        if (!item.childs.containsKey(key))
            item.childs.put(key,new Item());

        if(step==liter.length()-1) {
            item.childs.get(key).NumbersLine.add(new LiterAndNumberLine(pointLine,liter));
            return;
        }

                step++;
        rekursTree(pointLine, liter,(Item)item.childs.get(key),step);
    }

    //метод для проведения поиска в дереве.
    //Результат поиска записывает в поле класса ResultFind
    public void findInTree( Item item, int step, String liter){
        String readLine;
        int length=item.arrNumbersLine.length;
        if(step==liter.length()) {
            try {
                for (int i = 0; i < length; i++) {

                    File.seek(item.arrNumbersLine[i]);
                    ResultFind.add(File.readLine());
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(step==3){
            try {
                String parseLine;
                for (int i = 0; i < length; i++) {

                    File.seek(item.arrNumbersLine[i]);
                    readLine=File.readLine();
                    parseLine=readLine.split(",")[column];

                    if (parseLine.toLowerCase().startsWith(liter))
                        ResultFind.add(readLine);
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        char key = liter.charAt(step);
        if(item.childs.containsKey(key)) {
            step++;
            findInTree( item.childs.get(key), step, liter);
        }
    }
}