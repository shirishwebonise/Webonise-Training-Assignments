package main.java;

public class MergeSort implements Sorter{
	private int[] array;
	
	MergeSort(int[] array){
		this.array = array;
	}
	
	@Override
	public int[] sort() throws Exception{
		mergeSort(0, this.array.length-1);
		return this.array;
	}

	public void mergeSort(int startIndex, int endIndex ) throws Exception{
		
		if(startIndex < endIndex){
			int midIndex = (startIndex + endIndex)/2;		
			mergeSort(startIndex, midIndex);
			mergeSort(midIndex+1, endIndex);
			merge(startIndex, endIndex);
		}
	}

	public void merge(int startIndex, int endIndex) throws Exception{
		int i=0;
		int i1 = startIndex;
		int length1 = (startIndex+endIndex)/2;
		int i2 = length1 + 1;
		int length2 = endIndex;
		
		int[] tempArray = new int[endIndex-startIndex+1];
		
		while(i1 <= length1 && i2 <= length2){
			if(this.array[i1]<this.array[i2]){
				tempArray[i++] = this.array[i1++];
			}
			else{
				tempArray[i++] = this.array[i2++];
			}
		}
		while(i1<=length1){
			tempArray[i++] = this.array[i1++];
		}
		while(i2<=length2){
			tempArray[i++] = this.array[i2++];
		}
		
		for(int j=0; j<tempArray.length; j++){
			this.array[startIndex+j] = tempArray[j];
		}
	}
	
	
}
