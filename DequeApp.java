package algorithmsnstructures;

public class DequeApp {
    public static void main(String[] args) {
        Deque deq = new Deque(5);
        deq.insertRight(10); // Вставка 4 элементов
        deq.insertRight(20);
        deq.insertRight(30);
        deq.insertRight(40);
        deq.removeRight(); // Извлечение 3 элементов
        deq.removeRight(); // (10, 20, 30)
        deq.removeRight();
        deq.insertRight(50); // Вставка еще 4 элементов
        deq.insertRight(60); // (с циклическим переносом)
        deq.insertRight(70);
        deq.insertRight(80);
        while(!deq.isEmpty()){
            int el = deq.removeRight();
            System.out.println(el + " ");
        }
    }
}

class Deque{
    private int dequeArr[];
    private int left;
    private int right;
    private int dElems;
    private int size;

    public Deque(int size){
        this.size = size;
        dequeArr = new int[this.size];
        left = 0;
        right = 0;
        dElems = 0;
    }

    public void insertLeft(int value){
        dequeArr[left--] = value;
        dElems++;
        if(left == -1) left = size-1;
    }

    public int removeLeft(){
        if(++left == size) left = 0;

        int element = dequeArr[left];
        dequeArr[left] = 0;
        dElems--;

        return element;
    }

    public void insertRight(int value){
        dequeArr[right++] = value;
        dElems++;
        if (right == size ) right = 0;
    }

    public int removeRight(){
        if(--right == -1) right = size - 1;

        int element = dequeArr[right];
        dequeArr[right] = 0;
        dElems--;

        return element;
    }

    public boolean isFull(){
        return (dElems == size);
    }

    public boolean isEmpty(){
        return (dElems==0);
    }
}
