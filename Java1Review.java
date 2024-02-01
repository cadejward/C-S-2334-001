/**
 * Implement each of the 10 methods tested in JUnitTests.java. Study the tests
 * to determine how the methods should work.
 */
public class Java1Review {
	
	public static double divide(double a, double b) {
        if (b == 0.0) {

        	return Double.POSITIVE_INFINITY;
        } else {

        	return a / b;
        }
    }

    public static int divide(int a, int b) {
        if (b == 0) {

        	throw new ArithmeticException("Cannot divide by zero");
        } else {

        	return a / b;
        }
    }
	
        public static boolean isDivisibleBy7(int number) {
            return number % 7 == 0;
	}
        
        public static int findMin(int a, int b, int c) {
            return Math.min(Math.min(a, b), c);
        }
        
        public static int findMin(int[] array) {
            if (array.length == 0) {
                throw new IllegalArgumentException("Array is empty");
            }

            int min = array[0];

            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }

            return min;
        }
        
        public static double average(int[] array) {
            
            if (array.length == 0) {
                throw new IllegalArgumentException("Array is empty");
            }

            
            int sum = 0;
            for (int num : array) {
                sum += num;
            }

            
            return (double) sum / array.length;
        }
        
        public static void toLowerCase(String[] strings) {
            
            if (strings.length == 0) {
                throw new IllegalArgumentException("Array is empty");
            }

            
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].toLowerCase();
            }
        }
        public static String[] toLowerCaseCopy(String[] strings) {
            
            if (strings.length == 0) {
                throw new IllegalArgumentException("Array is empty");
            }

            
            String[] result = new String[strings.length];

            
            for (int i = 0; i < strings.length; i++) {
                result[i] = strings[i].toLowerCase();
            }

            return result;
        }

        public static void removeDuplicates(int[] array) {
            for (int i = 1; i < array.length; i++) {
                if (array[i] != 0) {
                    for (int j = i + 1; j < array.length; j++) {
                        if (array[i] == array[j]) {
                            array[i] = array[j] = 0;
                        }
                    }
                }
            }
        }

	public static void main(String[] args) {
		// If you want to write your own tests, do so here. (Do not modify
		// JUnitTests.java.) To run this method in Eclipse, right-click
		// Java1Review.java in the Package Explorer and select "Run As" >
		// "Java Application" from the context menu.
	}
	public static String main(String arg) {
        return "Overloaded main method was passed \"" + arg + "\".";
    }

}
