package algorithmsnstructures;

class SimpleSortsApp {
    public static void main(String[] args) {
        int maxSize = 100_000; // Размер массива

        HighArray arr, arr0; // Ссылка на массив
        long startTime,endTime, duration;

        arr = new HighArray(maxSize);
        arr0 = new HighArray(maxSize);// Создание массива

        arr.fillWNumsAsc();
        arr0.fillWNumsDesc();

        startTime = System.nanoTime();
        bubbleSort(arr); // Пузырьковая сортировка элементов
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the first arr time bubblesort took is " + duration/ 1_000_000.0 + "ms");

        startTime = System.nanoTime();
        bubbleSort(arr0); // Пузырьковая сортировка элементов
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the second arr time bubblesort took is " + duration/ 1_000_000.0 + "ms");

        arr.fillWNumsAsc();
        arr0.fillWNumsDesc();

        startTime = System.nanoTime();
        insertionSort(arr); // Сортировка вставкой
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the first arr time insertionSort took is " + duration/ 1_000_000.0 + "ms");

        startTime = System.nanoTime();
        insertionSort(arr0); // Сортировка вставкой
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the second arr time insertionSort took is " + duration/ 1_000_000.0 + "ms");

        arr.fillWNumsAsc();
        arr0.fillWNumsDesc();

        startTime = System.nanoTime();
        selectionSort(arr); // Сортировка вставкой
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the first arr time selectionSort took is " + duration/ 1_000_000.0 + "ms");

        startTime = System.nanoTime();
        selectionSort(arr0); // Сортировка вставкой
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("for the second arr time selectionSort took is " + duration/ 1_000_000.0 + "ms");

        HighArray array = new HighArray(10);
        array.fillWNumsDesc();
        array.display();
        array.fillWNumsAsc();
        array.display();
    }

    public static void bubbleSort(Array array) {
        int out, in;
        for (out = array.size() - 1; out > 1; out--) // Внешний цикл (обратный)
            for (in = 0; in < out; in++) // Внутренний цикл (прямой)
                if (array.getEl(in) > array.getEl(in + 1))
                    array.swap(in, in + 1);
    }

    public static void insertionSort(Array array) {
        int in, out;
        for(out=1; out<array.size(); out++) {
            long temp = array.getEl(out);
            in = out;
            while(in>0 && array.getEl(in-1) >= temp) {
                array.insertInIndex(in, array.getEl(in-1));
                --in;
            }
            array.insertInIndex(in, temp);
        }
    }

    public static void selectionSort(Array array) {
        int out, in, min;
        for(out=0; out<array.size()-1; out++) // Внешний цикл
        {
            min = out; // Минимум
            for(in=out+1; in<array.size(); in++) // Внутренний цикл
                if(array.getEl(in) < array.getEl(min)) // Если значение min больше,
                    min = in; // значит, найден новый минимум
            array.swap(out, min); // swap them
        }
    }
}