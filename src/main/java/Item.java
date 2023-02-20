import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    HashMap<Character,Item>childs;//ссылки на дочерние элементы
    ArrayList<LiterAndNumberLine> NumbersLine;//адреса строк для считывания
    Long[] arrNumbersLine;//адреса строк для считывания (заполняется при оптимизации)
    public Item()
    {
        childs= new HashMap<Character,Item>();
        NumbersLine = new ArrayList<LiterAndNumberLine>();
    }
}
