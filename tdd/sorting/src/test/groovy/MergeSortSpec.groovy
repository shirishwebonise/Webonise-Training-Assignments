
import main.java.MergeSort
import main.java.Sorter
import spock.lang.Specification


class MergeSortSpec extends Specification{	
    def "sort an array"(int[] arr, expectedResult) {
        setup:
	        Sorter mergeSort = new MergeSort(arr)
		
        when:
        	def result = mergeSort.sort()
		
        then:
        	result == expectedResult
		
		where:
			arr				| expectedResult
			[]				| []
			[1]				| [1]
			[2, 1]			| [1, 2]
			[5, 2, 0, 2, 8] | [0, 2, 2, 5, 8]
    }
}
