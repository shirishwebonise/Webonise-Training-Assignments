package main.java;

public class QuickSort implements Sorter{
	private int[] array;
	
	QuickSort(int[] arr){
		this.array = arr;
	}
	
	@Override
	public int[] sort() throws Exception {
		if(this.array.length > 0 )
        quickSort(0, this.array.length - 1);
		return this.array;
	}
	
	private void quickSort(int startIndex, int endIndex) {
		
        int i = startIndex;
        int j = endIndex;
        
        int midIndex = (startIndex + endIndex)/2;
        
        int pivot = this.array[midIndex];
        
        while (i <= j) {
            while (this.array[i] < pivot) {
                i++;
            }
            while (this.array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swapNumbers(i, j);
                i++;
                j--;
            }
        }
        
        if (startIndex < j)
            quickSort(startIndex, j);
        if (i < endIndex)
            quickSort(i, endIndex);
    }
 
    private void swapNumbers(int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }
}
