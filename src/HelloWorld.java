import List.Stack;

public class HelloWorld {

	public static void main(String[] args) {
		//System.out.println("Hello World");
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<15; i++) {
			stack.push(i);
		}
		
		System.out.println(stack);
		
		for(int i=0; i<15; i++) {
			System.out.print(stack.peek() +" ");
			stack.pop();

		}
		
		System.out.println(stack);
		
	}

}
