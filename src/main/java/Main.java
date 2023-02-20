import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static ListTree lt;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println("���������� �����");

        int column=0;
        try {
            String col = args[0];
            column = Integer.parseInt(col);
            if (column>14 || column<1)
            {
                System.out.println("� ���������� ��������� ���������� ������� ����� �� 1 �� 14");
                return;
            }
        }
        catch(Exception e){
            System.out.println("� ���������� ��������� ���������� ������� ����� �� 1 �� 14");
            column=1;
        }

        column--;
        Item item;
        try{
            item = OpenFile(column);
        }
            catch (IOException error){
            System.out.println("����� ��� ������ �����: "+ error.getMessage());
            return;
        }
        OptimisationTree(item);
        findInTree(item,column);
    }

    //���������� ������ "����������"
    public static Item OpenFile(int column) throws FileNotFoundException, IOException {
        Item item = new Item();
            RandomAccessFile file = new RandomAccessFile("airports.csv","r");
            lt = new ListTree(file,column);
            String line;
            long point=0;
            String arrstr[];
            long lengthFile = file.length();
            //���������� ���������� �����
            //��� ���������� ���������� ������ ��������������� ������� column
            while(lengthFile>=point){
                line = file.readLine();
                if (line==null)
                    break;
                arrstr= line.split(",");
                int k=0;
                for (String str: arrstr) {
                    if(str.equals("5001"))
                        System.out.println();
                    if (k==column)
                        lt.rekursTree(point,str.toLowerCase(),item,0);
                    k++;
                }
                point = file.getFilePointer();
            }
            file.seek(point);

        return item;
    }

    //����� � ������
    public static void findInTree(Item item,int column)
    {
        Scanner in = new Scanner(System.in);
        String str;
        while(true) {
            System.out.println();
            System.out.println("������� �����");
            str = in.nextLine();
            if (str.equals("!quit"))
                break;
            lt.ResultFind.clear();
            long time = System.currentTimeMillis();

            // �������
            try{
                Float.parseFloat(str);
            }
            catch (Exception e){
                if(str.length()>0)
                    str = "\""+str.toLowerCase();
            }
            //����� � ������
            lt.findInTree( item, 0, str);

            time = System.currentTimeMillis() - time;
            for (int i = 0; i < lt.ResultFind.size(); i++)
                System.out.println(lt.ResultFind.get(i).split(",")[column]+"["+lt.ResultFind.get(i)+"]");

            System.out.println("���������� ��������� �����  "+lt.ResultFind.size());
            System.out.println("����������� �� ����� �����  "+time);
        }
    }

    public static void OptimisationTree(Item item){
        lt.OptimisationTree(item);
    }
}

