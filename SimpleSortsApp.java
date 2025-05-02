package algorithmsnstructures;

import static algorithmsnstructures.QuickSelect.quickSelect;

class SimpleSortsApp {
    public static void main(String[] args) {
        int SIZE = 10000;

        HighArray arr0 = new HighArray(SIZE);
        HighArray arr1 = new HighArray(SIZE);

        arr0.fillWRandNums(SIZE);
        arr1.fillWRandNums(SIZE);

        Timer timer = new Timer();

        timer.start();
        singleThreadOddEvenSort(arr0);
        timer.stop();
        System.out.println("single thread sorting took " + timer.getTime());

        timer.start();
        multiThreadOddEvenSort(arr1);
        timer.stop();
        System.out.println("multi thread sorting took " + timer.getTime());
    }

    public static void singleThreadOddEvenSort(Array array){
        HighArray oddArray, evenArray;

        int newSize = array.size() / 2;
        int oddSize, evenSize;
        if(array.size() % 2 == 0){
            oddSize = newSize;
            evenSize = newSize;
        } else {
            oddSize = newSize;
            evenSize = newSize+1;
        }

        oddArray = new HighArray(oddSize);
        evenArray = new HighArray(evenSize);

        for(int index = 0; index < array.size(); index++){
            if(index%2 == 0){
                evenArray.insert(array.getEl(index));
            } else{
                oddArray.insert(array.getEl(index));
            }
        }

        insertionSort(evenArray);
        insertionSort(oddArray);

        int oddIndex = 0, evenIndex = 0;
        for(int index = 0; index <array.size(); index++){
            if (oddSize != oddIndex && evenSize != evenIndex) {
                long oddVal = oddArray.getEl(oddIndex);
                long evenVal = evenArray.getEl(evenIndex);
                array.insertInIndex(index, (evenVal < oddVal) ? evenVal : oddVal);
                if (evenVal < oddVal) {
                    evenIndex++;
                } else {
                    oddIndex++;
                }
            } else if(oddIndex<oddSize){
                array.insertInIndex(index, oddArray.getEl(oddIndex++));
            } else{
                array.insertInIndex(index, evenArray.getEl(evenIndex++));
            }
        }
    }

    public static void multiThreadOddEvenSort(Array array){
        HighArray oddArray, evenArray;

        int newSize = array.size() / 2;
        int oddSize, evenSize;
        if(array.size() % 2 == 0){
            oddSize = newSize;
            evenSize = newSize;
        } else {
            oddSize = newSize;
            evenSize = newSize+1;
        }

        oddArray = new HighArray(oddSize);
        evenArray = new HighArray(evenSize);

        for(int index = 0; index < array.size(); index++){
            if(index%2 == 0){
                evenArray.insert(array.getEl(index));
            } else{
                oddArray.insert(array.getEl(index));
            }
        }

        Thread evenSortThread = new Thread(() -> insertionSort(evenArray));
        Thread oddSortThread = new Thread(() -> insertionSort(oddArray));

        evenSortThread.start();
        oddSortThread.start();

        try {
            evenSortThread.join();
            oddSortThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int oddIndex = 0, evenIndex = 0;
        for(int index = 0; index <array.size(); index++){
            if (oddSize != oddIndex && evenSize != evenIndex) {
                long oddVal = oddArray.getEl(oddIndex);
                long evenVal = evenArray.getEl(evenIndex);
                array.insertInIndex(index, (evenVal < oddVal) ? evenVal : oddVal);
                if (evenVal < oddVal) {
                    evenIndex++;
                } else {
                    oddIndex++;
                }
            } else if(oddIndex<oddSize){
                array.insertInIndex(index, oddArray.getEl(oddIndex++));
            } else{
                array.insertInIndex(index, evenArray.getEl(evenIndex++));
            }
        }
    }

    public static void cocktailSort(Array array) {
        int outMax = array.size() - 1;
        int outMin = 0;
        boolean swapped;
        int in, j;

        while(outMax>outMin){
            swapped = false;

            for (in = outMin; in < outMax; in++) {
                j = in + 1;
                //System.out.println("in first cycle comparing el " + array.getEl(in) + " with index " + in + " and el " + array.getEl(in + 1) + " with index " + j);
                if (array.getEl(in) > array.getEl(in + 1)) {
                    array.swap(in, in + 1);
                    swapped = true;
                }
            }
            outMax--;

            for (in = outMax; in > outMin; in--) {
                j = in - 1;
                //System.out.println("in second cycle comparing el " + array.getEl(in) + " with index " + in + " and el " + array.getEl(in - 1) + " with index " + j);
                if (array.getEl(in) < array.getEl(in - 1)) {
                    array.swap(in, in - 1);
                    swapped = true;
                }
            }
            outMin++;

            if(!swapped) break;
        }
    }

    public static void bubbleSort(Array array) {
        int out, in;
        int comparisonCount = 0, permutationCount = 0;
        for (out = array.size() - 1; out > 0; out--)// Внешний цикл (обратный)
            for (in = 0; in < out; in++) {// Внутренний цикл (прямой)
                comparisonCount++;
                if (array.getEl(in) > array.getEl(in + 1))
                    permutationCount+=2;
                    array.swap(in, in + 1);
            }
//        System.out.println("amount of comparison is " + comparisonCount);
//        System.out.println("amount of permutation is " + permutationCount);
    }

    public static void insertionSort(Array array) {
        int in, out;
        int comparisonCount = 0, permutationCount = 0;
        for(out=1; out<array.size(); out++) {
            long temp = array.getEl(out);
            in = out;
            while(in>0) {
                comparisonCount++;
                if (array.getEl(in - 1) >= temp) {
                    array.insertInIndex(in, array.getEl(in - 1));
                    permutationCount++;
                    --in;
                } else{
                    break;
                }
            }
            array.insertInIndex(in, temp);
        }

//        System.out.println("amount of comparison is " + comparisonCount);
//        System.out.println("amount of permutation is " + permutationCount);
    }

    public static void insertionSortWDubsDeleting(Array array){
        int in, out, dups = 0;
        for(out=1; out<array.size(); out++) {
            long temp = array.getEl(out);
            in = out;
            while(in>0) {
                if (array.getEl(in - 1) > temp) {
                    array.insertInIndex(in, array.getEl(in - 1));
                    --in;
                } else if(array.getEl(in-1) == temp){
                    array.insertInIndex(in-1, -1);
                    ++dups;
                } else{
                    break;
                }
            }
            array.insertInIndex(in, temp);
        }
        array.cutUselessElements();
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