import main.java.QuickSort;
import main.java.Sorter;
import spock.lang.Specification;

class QuickSortSpec extends Specification{	
	def "sort an array"(int[] arr, expectedResult) {
		setup:
			Sorter quickSort = new QuickSort(arr)
		
		when:
			def result = quickSort.sort()
		
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