package algorithmsnstructures;


public class QueueApp {
    public static void main(String[] args) {
        Queue theQueue = new Queue(5); // Очередь из 5 ячеек
        theQueue.insert(10); // Вставка 4 элементов
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);
        theQueue.remove(); // Извлечение 3 элементов
        theQueue.remove(); // (10, 20, 30)
        theQueue.remove();
        theQueue.insert(50); // Вставка еще 4 элементов
        theQueue.insert(60); // (с циклическим переносом)
        theQueue.insert(70);
        theQueue.insert(80);
        theQueue.display();
    }
}

class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;
    //--------------------------------------------------------------
    public Queue(int s) // Конструктор
    {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }
    //--------------------------------------------------------------
    public void insert(long j) // Вставка элемента в конец очереди
    {
        if(rear == maxSize-1) // Циклический перенос
            rear = -1;
        queArray[++rear] = j; // Увеличение rear и вставка
        nItems++; // Увеличение количества элементов
    }
    //--------------------------------------------------------------
    public long remove() // Извлечение элемента в начале очереди
    {
        long temp = queArray[front++]; // Выборка и увеличение front
        if(front == maxSize) // Циклический перенос
            front = 0;
        nItems--; // Уменьшение количества элементов
        return temp;
    }
    //--------------------------------------------------------------
    public long peekFront() // Чтение элемента в начале очереди
    {
        return queArray[front];
    }
    //--------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (nItems==0);
    }
    public boolean isFull()
    {
        return (nItems==maxSize);
    }
    public int size() // Количество элементов в очереди
    {
        return nItems;
    }
    public void display(){
        if(nItems==0) {
            System.out.println("queue is empty");
            return;
        }
        while(front!=rear) {
            System.out.println("index is " + front + " value is " + queArray[front] + " ");
            front++;
            if (front==nItems) front = 0;
        }
        System.out.println("index is " + rear + " value is " + queArray[rear]);
    }
}