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

//Ordered Array
class OrdArray
{
    private long[] a;
    private int nElems;

    public OrdArray() {}
    public OrdArray(int max)
    {
        a = new long[max];
        nElems = 0;
    }
    public int size()
    { return nElems; }
    public long getEl(int index){
        return a[index];
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
                return nElems;
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

    public void display()
    {
        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
        System.out.println("");
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
}


//Default Array
class HighArray {
    private long[] a;
    private int nElems;

    public HighArray(int max)
    {
        a = new long[max];
        nElems = 0;
    }
    public boolean find(long searchKey)
    {
        int j;
        for(j=0; j<nElems; j++)
            if(a[j] == searchKey)
                break;
        if(j == nElems)
            return false;
        else
            return true;
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

    public void display()
    {
        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
        System.out.println("всего элементов " + nElems);
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


