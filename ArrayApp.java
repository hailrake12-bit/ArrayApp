package algorithmsnstructures;

import static print.Print.*;
import java.util.*;

public class ArrayApp {
    public static void main(String[] args) {
        Random rand = new Random();
        int size = rand.nextInt(10, 16);
        HighArray arr = new HighArray(size);
        for(int index = 0; index < size; index++){
            long element = rand.nextLong(100);
            arr.insert(element);
            if(index % 4 == 0 && index+1!=size) {
                arr.insert(element);
                index++;
            }
        }
        arr.display();
        arr.noDups();
        arr.display();
    }
}


abstract class Array{
    Random random = new Random();
    protected long[] a;
    protected int nElems;

    protected Array(){}
    protected Array(int size){
        a = new long[size];
        nElems = size;
    }

    public int size() {
        return nElems;
    }
    public long getEl(int index){
        return a[index];
    }
    public void insertInIndex(int index, long temp){
        a[index] = temp;
    }
    public void display() {
        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
        System.out.print("Всего элементов " + nElems);
        System.out.println("");
    }
    public void swap(int one, int two){
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
    public void fillWRandNums(int limit){
        for(int index = 0; index < nElems; index++){
            a[index] = random.nextLong(limit);
        }
    }
    public void fillWNumsDesc(){
        for(int index = 0; index < nElems; index++){
            a[index] = nElems - index - 1;
        }
    }
    public void fillWNumsAsc(){
        for(int index = 0; index < nElems; index++){
            a[index] = index;
        }
    }
    abstract public int find(long searchKey);
    abstract public boolean delete(long value);
    abstract public void insert(long value);
}


//Ordered Array
class OrdArray extends Array
{
    public OrdArray(int max)
    {
        super(max);
    }

    public int find(long searchKey)
    {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;
        while(true)
        {
            curIn = (lowerBound + upperBound ) / 2;
            if(a[curIn]==searchKey)
                return curIn;
            else if(lowerBound > upperBound)
                return -1;
            else
            {
                if(a[curIn] < searchKey)
                    lowerBound = curIn + 1;
                else
                    upperBound = curIn - 1;
            }
        }
    }

    public void insert(long value) {
        int left = 0, right = nElems - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;
            if (a[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        for (int k = nElems; k > left; k--) {
            a[k] = a[k - 1];
        }

        a[left] = value;
        nElems++;
    }

    public boolean delete(long value)
    {
        int j = find(value);
        if(j==nElems) // Найти не удалось
            return false;
        else // Элемент найден
        {
            for(int k=j; k<nElems; k++) // Перемещение последующих элементов
                a[k] = a[k+1];
            nElems--;
            return true;
        }
    }

    public void merge(OrdArray firstArr, OrdArray secondArr){
        int newSize =  firstArr.size() + secondArr.size();
        a = new long[newSize];
        int firstIndex = 0, secondIndex = 0, newIndex = 0;

        if (firstArr.size() == 0) {
            while (secondIndex < secondArr.size())
                a[newIndex++] = secondArr.getEl(secondIndex++);
            this.nElems = newIndex;
            return;
        } else if (secondArr.size() == 0) {
            while (firstIndex < firstArr.size())
                a[newIndex++] = firstArr.getEl(firstIndex++);
            this.nElems = newIndex;
            return;
        }

        while(firstIndex!=(firstArr.size()) && secondIndex != (secondArr.size())) {
            if (firstArr.getEl(firstIndex) < secondArr.getEl(secondIndex)) {
                a[newIndex++] = firstArr.getEl(firstIndex++);
            } else
                a[newIndex++] = secondArr.getEl(secondIndex++);
        }

        while (firstIndex < firstArr.size())
            a[newIndex++] = firstArr.getEl(firstIndex++);

        while (secondIndex < secondArr.size())
            a[newIndex++] = secondArr.getEl(secondIndex++);

        this.nElems = newIndex;
    }

    public void swap(int one, int two)
    {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
}


//Default Array
class HighArray extends Array {
    public HighArray(){}
    public HighArray(int max) {
        super(max);
    }

    public int find(long searchKey)
    {
        int j;
        for(j=0; j<nElems; j++)
            if(a[j] == searchKey)
                return j;
        return -1;
    }


    public void insert(long value)
    {
        a[nElems] = value;
        nElems++;
    }

    public boolean delete(long value)
    {
        int j;
        for(j=0; j<nElems; j++)
            if( value == a[j] )
                break;
        if(j==nElems)
            return false;
        else
        {
            for(int k=j; k<nElems; k++)
                a[k] = a[k+1];
            nElems--;
            return true;
        }
    }

    public long removeMax(){
        if(nElems == 0) return -1;
        else {
            int indexMax = 0;
            for (int index = 0; index < nElems; index++) {
                if(a[index]>a[indexMax]) indexMax = index;
            }
            long max = a[indexMax];
            for (int index = indexMax; index < nElems; index++){
                a[index] = a[index+1];
            }
            nElems--;
            return max;
        }
    }

    public void noDups(){
        Arrays.sort(a);

        int uniqueIndex = 1;

        for(int i = 1; i < nElems; i++){
            if(a[i-1] != a[i]) a[uniqueIndex++] = a[i];
        }

        long arr[] = Arrays.copyOfRange(a, 0,uniqueIndex );
        a = arr;
        this.nElems = uniqueIndex;
    }

}


